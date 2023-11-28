package com.jie.spring.context.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.ConfigurableListableBeanFactory;
import com.jie.spring.beans.factory.support.DefaultListableBeaFactory;

/**
 * 获取beaFactory和加载资源
 *
 * @author jie
 * @date 2023/11/27 23:16
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeaFactory beaFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        // 获取DefaultListableBeaFactory的实例化
        DefaultListableBeaFactory beanFactory = createBeanFactory();
        // 对资源配置的加载操作，完成对spring.xml配置文件中Bean对象的定义和注册
        loadBeanDefinitions(beanFactory);
        this.beaFactory = beanFactory;
    }

    private DefaultListableBeaFactory createBeanFactory() {
        return new DefaultListableBeaFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeaFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return this.beaFactory;
    }
}
