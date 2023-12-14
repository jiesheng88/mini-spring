package com.jie.spring.beans.factory;

import com.jie.spring.context.support.ClassPathXmlApplicationContext;
import com.jie.spring.test.UserService;
import org.junit.Test;

/**
 * @author jie
 * @date 2023/12/14 22:53
 */
public class AwareTest {

    @Test
    public void testAware() {
        // 1、初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_basic.xml");
        applicationContext.registerShutdownHook();

        // 2、获取 Bean 对象，调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.getUserInfo();
        System.out.println("userInfo: " + userInfo);
        System.out.println("ApplicationContext: " + userService.getApplicationContext());
        System.out.println("BeanFactory: " + userService.getBeanFactory());

    }

}
