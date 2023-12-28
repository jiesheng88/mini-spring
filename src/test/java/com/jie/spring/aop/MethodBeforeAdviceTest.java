package com.jie.spring.aop;

import com.jie.spring.context.support.ClassPathXmlApplicationContext;
import com.jie.spring.test.aop.AopProxyIUserService;
import org.junit.Test;

/**
 * @author jie
 * @date 2023/12/28 22:11
 */
public class MethodBeforeAdviceTest {

    @Test
    public void test_methodBeforeAdvice() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_methodbeaforeadvice.xml");
        AopProxyIUserService userService = applicationContext.getBean("userService", AopProxyIUserService.class);

        System.out.println("测试结果：" + userService.queryUserInfo());
        System.out.println("测试结果：" + userService.register("jie"));
    }
}
