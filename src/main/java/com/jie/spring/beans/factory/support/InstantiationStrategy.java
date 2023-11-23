package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口
 *
 * @author jie
 * @date 2023/11/23 22:40
 */
public interface InstantiationStrategy {
    /**
     * 根据入参实例化Bean
     *
     * @param beanName
     * @param beanDefinition
     * @param constructor
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) throws BeansException;
}
