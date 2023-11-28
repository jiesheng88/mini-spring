package com.jie.spring.common;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.config.BeanPostProcessor;
import com.jie.spring.test.UserService;

/**
 * @author jie
 * @date 2023/11/28 23:08
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("userService")) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object potProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
