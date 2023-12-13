package com.jie.spring.beans.factory;

import com.jie.spring.context.support.ClassPathXmlApplicationContext;
import com.jie.spring.test.UserService;
import org.junit.Test;

/**
 * @author jie
 * @date 2023/12/13 23:32
 */
public class InitializingBeanAndDisposableBeanTest {

    @Test
    public void testInitializingBeanAndDisposableBean() {
        // 1、初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_basic.xml");
        // 注册钩子
        applicationContext.registerShutdownHook();

        // 2、获取Bean对象，并调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.getUserInfo();
        System.out.println("测试结果：" + userInfo);
    }

}
