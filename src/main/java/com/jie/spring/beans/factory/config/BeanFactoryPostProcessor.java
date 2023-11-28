package com.jie.spring.beans.factory.config;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor，是由 Spring 框架组建提供的容器扩展机制，允许在 Bean 对象注册后但未实例化之前，
 * 对 Bean 的定义信息 `BeanDefinition` 执行修改操作。
 *
 * @author jie
 * @date 2023/11/27 22:42
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有的BeanDefinition加载完成后，实例化Bean对象之前，提供修改BeanDefinition属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
