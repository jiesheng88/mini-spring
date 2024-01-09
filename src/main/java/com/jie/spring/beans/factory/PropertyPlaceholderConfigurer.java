package com.jie.spring.beans.factory;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.PropertyValue;
import com.jie.spring.beans.PropertyValues;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.jie.spring.core.io.DefaultResourceLoader;
import com.jie.spring.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * 处理占位符配置
 * 依赖于 BeanFactoryPostProcessor 在 Bean 生命周期的属性，可以在 Bean 对象实例化之前，改变属性信息。
 * 所以这里通过实现 BeanFactoryPostProcessor 接口，完成对配置文件的加载以及摘取占位符中的在属性文件里的配置。
 *
 * @author jie
 * @date 2024/1/8 23:18
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * 默认的占位符前缀：{@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    /**
     * 默认的占位符后缀：{@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            // Properties 类常用于存储程序的配置信息，例如数据库连接信息、日志输出配置、应用程序设置等。
            // 使用Properties类，可以将这些信息存储在一个文本文件中，并在程序中读取这些信息。
            Properties properties = new Properties();
            // 从输入流中读取属性列表（键和元素对）。
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    // Properties 中一般保存的是 String 类型的
                    if (!(value instanceof String)) {
                        continue;
                    }
                    // Spring.xml 中配置格式如下：
                    // <property name="token" value="${token}">
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIndex = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIndex != -1 && stopIndex != -1 && startIndex < stopIndex) {
                        String propkey = strVal.substring(startIndex + 2, stopIndex);
                        String propVal = properties.getProperty(propkey);
                        // 把提取到的配置信息放置到属性配置中
                        buffer.replace(startIndex, stopIndex + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
