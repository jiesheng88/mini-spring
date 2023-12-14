package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.BeanPostProcessor;
import com.jie.spring.beans.factory.config.ConfigurableBeanFactory;
import com.jie.spring.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类定义模板方法
 * 使用模板模式 (opens new window)的设计方式，可以统一收口通用核心方法的调用逻辑和标准定义，也就很好的控制了后续的实现者不用关心调用逻辑，
 * 按照统一方式执行。那么类的继承者只需要关心具体方法的逻辑实现即可。
 * <p>
 * AbstractBeanFactory 抽象类只会实现属于自己的抽象方法，其他抽象方法由继承类实现。这里就体现了类实现过程中的各司其职，
 * 你只需要关心属于你的内容，不是你的内容，不要参与。
 * <p>
 * AbstractBeanFactory：已经具备了单例Bean的注册功能和获取功能。
 *
 * @author jie
 * @date 2023/11/21 22:51
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    /**
     * BeanPostProcessors to apply in createBean
     * 创建Bean时，使用到，用于拓展 Bean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    /**
     * ClassLoader to resolve bean class names with, if necessary
     * ClassLoader 来解析 bean 类名
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(String name, Object[] args) {
        // 对单例 Bean 对象的获取以及在获取不到时需要拿到 Bean 的定义做相应 Bean 实例化操作
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        // 只定义了调用过程以及提供了抽象方法，由实现此抽象类的其他类做相应实现。
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
