package com.jie.spring.context.annotation;

import com.jie.spring.context.support.ClassPathXmlApplicationContext;
import com.jie.spring.test.aop.AopProxyIUserService;
import org.junit.Test;


/**
 * @author jie
 * @date 2024/1/8 23:07
 */
public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring_scan.xml");
        AopProxyIUserService userService = context.getBean("userService", AopProxyIUserService.class);
        String info = userService.queryUserInfo();
        System.out.println(info);
    }

}
