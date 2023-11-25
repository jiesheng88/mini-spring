package com.jie.spring.beans.factory;

import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.support.DefaultListableBeaFactory;
import com.jie.spring.test.HelloService;
import com.jie.spring.test.UserService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * @author jie
 * @date 2023/11/22 23:25
 */
public class BeanDefinitionAndBeanDefinitionRegistryTest {

    @Test
    public void testBeanFactory_succeed_noParamStructure() {
        // 1、初始化BeanFactory
        DefaultListableBeaFactory beanFactory = new DefaultListableBeaFactory();
        // 2、注册Bean
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("helloService", beanDefinition);
        // 3、第一次获取bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        assertThat(helloService).isNotNull();
        assertThat(helloService.sayHello()).isEqualTo("hello");

        // 4、第二次获取bean from singleton
        HelloService helloService_singleton = (HelloService) beanFactory.getBean("helloService");
        helloService_singleton.sayHello();

    }

    @Test
    public void testBeanFactory_failure_paramStructure() {
        // 断言异常
        // when
        Throwable thrown = catchThrowable(() -> {
            DefaultListableBeaFactory beaFactory = new DefaultListableBeaFactory();
            BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
            beaFactory.registerBeanDefinition("user", beanDefinition);
            UserService userService = (UserService) beaFactory.getBean("user");
        });

        // then
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("init bean with param construct");

    }
}
