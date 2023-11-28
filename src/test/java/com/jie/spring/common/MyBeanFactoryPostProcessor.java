package com.jie.spring.common;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.PropertyValue;
import com.jie.spring.beans.PropertyValues;
import com.jie.spring.beans.factory.ConfigurableListableBeanFactory;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author jie
 * @date 2023/11/28 23:05
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 在 BeanDefinition 加载完后，对 BeanDefinition 进行拓展
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
