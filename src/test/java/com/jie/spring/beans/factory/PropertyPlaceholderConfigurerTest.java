package com.jie.spring.beans.factory;

import com.jie.spring.context.support.ClassPathXmlApplicationContext;
import com.jie.spring.test.aop.AopProxyIUserService;
import org.junit.Test;

/**
 * @author jie
 * @date 2024/1/9 23:05
 */
public class PropertyPlaceholderConfigurerTest {

    @Test
    public void testPostProcessorBeanFactory() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_placeholder.xml");
        AopProxyIUserService userService = applicationContext.getBean("userService", AopProxyIUserService.class);
        System.out.println(userService);

    }
}
