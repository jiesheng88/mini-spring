package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册表接口
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BenDefinition
     *
     * @param beanNme
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanNme, BeanDefinition beanDefinition);

    /**
     * 根据名称获取BeanDefinition
     *
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 是否包含指定名称的BeanDefinition
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取所有BeanDefinition的名称
     * @return
     */
//    String[] getBeanDefinitionNames();

}
