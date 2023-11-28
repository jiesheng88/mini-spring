package com.jie.spring.beans.factory.config;

import com.jie.spring.beans.BeansException;

/**
 * BeanPostProcessor，也是 Spring 提供的扩展机制，不过 BeanPostProcessor 是在 Bean 对象实例化前后修改 Bean 对象，
 * 也可以替换 Bean 对象。BeanPostProcessor 也是实现 AOP 切面技术的关键所在
 *
 * @author jie
 * @date 2023/11/27 22:46
 */
public interface BeanPostProcessor {
    /**
     * 在Bean对象执行初始化之前，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object potProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}
