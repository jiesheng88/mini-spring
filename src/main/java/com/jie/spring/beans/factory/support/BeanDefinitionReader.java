package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.core.io.Resource;
import com.jie.spring.core.io.ResourceLoader;

/**
 * Bean 定义读取接口
 *
 * @author jie
 * @date 2023/11/26 17:32
 */
public interface BeanDefinitionReader {
    /**
     * getRegistry()、getResourceLoader() 都是用于提供给下面三个方法的工具，加载和注册
     * 两个方法的实现会包装到抽象类中，以免会污染具体的接口实现
     *
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    /**
     * 三个加载Bean定义的方法
     *
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
