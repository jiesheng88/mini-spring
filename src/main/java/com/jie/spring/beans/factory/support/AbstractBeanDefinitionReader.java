package com.jie.spring.beans.factory.support;

import com.jie.spring.core.io.DefaultResourceLoader;
import com.jie.spring.core.io.ResourceLoader;

/**
 * 抽象类对接口中的方法可以不全部进行实现
 * Bean定义抽象类实现
 * 向外部调用方提供构造函数，把Bean定义注入类，传递进来。
 * 在接口 BeanDefinitionReader 的具体实现类中，就可以把解析后的 XML 文件中的 Bean 信息，注册到 Spring 容器去了。
 *
 * @author jie
 * @date 2023/11/26 17:37
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
