package com.jie.spring.context.support;

import com.jie.spring.beans.factory.support.DefaultListableBeaFactory;
import com.jie.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.jie.spring.common.MyBeanFactoryPostProcessor;
import com.jie.spring.common.MyBeanPostProcessor;
import com.jie.spring.test.UserService;
import org.junit.Test;


/**
 * @author jie
 * @date 2023/11/28 22:58
 */
public class ClassPathXmlApplicationContextTest {
    /**
     * 不用应用上下文
     *
     * @throws Exception
     */
    @Test
    public void testBeanFactoryPostProcessorAndBeanPostProcessor_with_noContext() throws Exception {
        // 1、创建 BeanFactory
        DefaultListableBeaFactory beaFactory = new DefaultListableBeaFactory();

        // 2、配置加载，加载BeanDefinition
        String location = "classpath:spring_basic.xml";
//        String location = "src/test/resources/spring_basic.xml";
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beaFactory);
        reader.loadBeanDefinitions(location);

        // 3、BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor factoryPostProcessor = new MyBeanFactoryPostProcessor();
        factoryPostProcessor.postProcessorBeanFactory(beaFactory);

        // 4、Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beaFactory.addBeanPostProcessor(beanPostProcessor);

        // 5、获取修改后的Bean对象
        UserService newUserService = beaFactory.getBean("userService", UserService.class);
        String newUserInfo = newUserService.getUserInfo();
        System.out.println(newUserInfo);
    }

    /**
     * 使用上下文
     */
    @Test
    public void testBeanFactoryPostProcessorAndBeanPostProcessor_with_context() {
        // 1、初始化上下文
        String location = "classpath:spring_postProcessor.xml";
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(location);

        // 2、获取 Bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userInfo = userService.getUserInfo();
        System.out.println(userInfo);
    }

}
