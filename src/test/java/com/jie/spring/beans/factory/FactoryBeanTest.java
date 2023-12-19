package com.jie.spring.beans.factory;

import com.jie.spring.context.support.ClassPathXmlApplicationContext;
import com.jie.spring.test.IUserService;
import org.junit.Test;

/**
 * @author jie
 * @date 2023/12/18 23:12
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean_prototype() {
        // 1、初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_FactoryBean.xml");
        applicationContext.registerShutdownHook();

        // 2、获取 Bean 对象，调用方法
        IUserService iUserService1 = applicationContext.getBean("iUserService", IUserService.class);
        IUserService iUserService2 = applicationContext.getBean("iUserService", IUserService.class);

        // 3、输出对象
        System.out.println(iUserService1);
        System.out.println(iUserService2);

        // 4、打印十六进制哈希
        System.out.println(iUserService1 + "十六进制哈希：" + Integer.toHexString(iUserService1.hashCode()));
    }

    @Test
    public void test_FactoryBean_ProxyBean() {
        // 1、初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_FactoryBean.xml");
        applicationContext.registerShutdownHook();

        // 2、调用代理方法
        IUserService iUserService = applicationContext.getBean("iUserService", IUserService.class);
        String userInfo = iUserService.queryUserInfo();
        System.out.println(userInfo);
    }
}
