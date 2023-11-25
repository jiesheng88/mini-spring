package com.jie.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.PropertyValue;
import com.jie.spring.beans.PropertyValues;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 实例化Bean类
 * 实现继承的抽象类中的创建Bean方法
 *
 * @author jie
 * @date 2023/11/21 23:18
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            // 利用策略模式来对Bean进行实例化操作
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 填充Bean的属性信息
            applyPropertyValues(bean, beanName, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 存放到单例对象的缓存中去
        addSingleton(beanName, bean);
        return bean;
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
}
