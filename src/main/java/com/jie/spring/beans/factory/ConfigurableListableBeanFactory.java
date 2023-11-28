package com.jie.spring.beans.factory;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.jie.spring.beans.factory.config.BeanDefinition;
import com.jie.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 * <p>
 * 提供分析和修改Bean以及预先实例化的操作接口，不过目前只有一个 getBeanDefinition 方法。
 * <p>
 * 接口可以多继承
 *
 * @author jie
 * @date 2023/11/26 21:12
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {
    /**
     * 根据beanName获取对于的BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 预先实例化Bean
     *
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;
}
