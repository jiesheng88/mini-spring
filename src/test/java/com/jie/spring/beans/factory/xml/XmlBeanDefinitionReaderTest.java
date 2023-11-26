package com.jie.spring.beans.factory.xml;

import com.jie.spring.beans.factory.support.DefaultListableBeaFactory;
import com.jie.spring.core.io.FileSystemResource;
import com.jie.spring.core.io.Resource;
import com.jie.spring.test.UserService;
import org.junit.Test;

/**
 * @author jie
 * @date 2023/11/26 20:04
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void testLoadBeanDefinitions() throws Exception {
        // 1、创建待读取文件的Resource
        String location = "src/main/resources/spring.xml";
        Resource resource = new FileSystemResource(location);

        // 2、创建 XmlBeanDefinitionReader
        // 创建BeanDefinitionRegistry
        DefaultListableBeaFactory beaFactory = new DefaultListableBeaFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beaFactory);

        // 3、传入Resource
//        xmlBeanDefinitionReader.loadBeanDefinitions(location);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);

        // 4、获取Bean
        UserService userService = beaFactory.getBean("userService", UserService.class);
        System.out.println(userService.getUserInfo());

    }

}
