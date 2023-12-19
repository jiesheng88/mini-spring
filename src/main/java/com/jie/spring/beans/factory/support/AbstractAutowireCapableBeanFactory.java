package com.jie.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.PropertyValue;
import com.jie.spring.beans.PropertyValues;
import com.jie.spring.beans.factory.Aware;
import com.jie.spring.beans.factory.BeanClassLoaderAware;
import com.jie.spring.beans.factory.BeanFactoryAware;
import com.jie.spring.beans.factory.BeanNameAware;
import com.jie.spring.beans.factory.DisposableBean;
import com.jie.spring.beans.factory.InitializingBean;
import com.jie.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.BeanPostProcessor;
import com.jie.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 实例化Bean类
 * 实现继承的抽象类中的创建Bean方法
 * 【增加】感知调用操作
 * 【增加】创建和修改对象时判断单例和原型模式
 *
 * @author jie
 * @date 2023/11/21 23:18
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        // 注册Bean的过程：
        // 1、Bean 实例化（创建一个空对象）
        // 2、Bean 初始化（填充属性，可以进行扩展）
        // 3、Bean 注册到容器中
        Object bean;
        try {
            // 利用策略模式来对Bean进行实例化操作（空对象）
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 填充Bean的属性信息
            applyPropertyValues(bean, beanName, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 增加注册 实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 判断是否是单例
        if (beanDefinition.isSingleton()) {
            // 存放到单例对象的缓存中去
            addSingleton(beanName, bean);
        }
        return bean;
    }

    /**
     * 单例模式和原型模式的区别就在于是否存放到内存中，如果是原型模式那么就不会存放到内存中，每次获取都重新创建对象，
     * 另外非 Singleton 类型的 Bean 不需要执行销毁方法。
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 非单例类型的 Bean 不执行销毁方法
        if (!beanDefinition.isSingleton()) {
            return;
        }

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            // 统一交给适配器来做统一处理
            // 销毁方法的注册交给父类 DefaultSingletonBeanRegistry 来处理
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    /**
     * Bean 初始化
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     */
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        // Constructor 代表了你有多少个构造函数，通过beanClass.getDeclaredConstructors()可以获取到你所有的构造函数，是一个集合
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        // 循环比对构造函数集合与入参args的匹配情况，这里只比对数量
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            if (args != null && declaredConstructor.getParameterTypes().length == args.length) {
                constructor = declaredConstructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanName, beanDefinition, constructor, args);
    }

    private InstantiationStrategy getInstantiationStrategy() {
        return this.instantiationStrategy;
    }

    /**
     * Bean 属性填充
     *
     * @param bean
     * @param beanName
     * @param beanDefinition
     */
    protected void applyPropertyValues(Object bean, String beanName, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();

            if (value instanceof BeanReference) {
                // A 依赖 B，需要先获取B的实例化
                // 如果依赖过多时，就会循环递归获取Bean实例
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            // 通过反射设置属性
            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // invokeAwareMethods，这样就能通知到已经实现了此接口的类
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }


        // 1、执行 BeanPostProcessor#before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 2、初始化Bean，（执行 spring.xml 中配置的 Bean 初始化方法和初始化接口中实现的）
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 3、执行 BeanPostProcessor#after 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1、实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            // 直接执行接口实现中定义的初始化方法
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 2、配置信息 init-method (判断是为了避免二次执行初始化)
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            // 通过反射的方式执行初始化方法
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (initMethod == null) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.potProcessorAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
