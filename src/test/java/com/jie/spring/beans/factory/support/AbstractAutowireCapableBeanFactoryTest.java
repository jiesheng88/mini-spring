package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.PropertyValue;
import com.jie.spring.beans.PropertyValues;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.BeanReference;
import com.jie.spring.test.UserDao;
import com.jie.spring.test.UserService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jie
 * @date 2023/11/25 23:11
 */
public class AbstractAutowireCapableBeanFactoryTest {

    @Test
    public void testApplyPropertyValues() {
        // 1、初始化beanFactory
        DefaultListableBeaFactory beanFactory = new DefaultListableBeaFactory();

        // 2、先手动注册前置Bean UserDao
        String userDaoBeanName = "userDao";
        beanFactory.registerBeanDefinition(userDaoBeanName, new BeanDefinition(UserDao.class));

        // 3、UserService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "marry"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4、UserService 注入Bean
        String userServiceBeanName = "userService";
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition(userServiceBeanName, beanDefinition);

        // 5、获取Bean
        UserService userService = (UserService) beanFactory.getBean(userServiceBeanName, "marry");
        assertThat(userService).isNotNull();
        System.out.println(userService.getUserInfo());

    }
}
