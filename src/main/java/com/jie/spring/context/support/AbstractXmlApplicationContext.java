package com.jie.spring.context.support;

import com.jie.spring.beans.factory.support.DefaultListableBeaFactory;
import com.jie.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中对配置信息的加载
 *
 * @author jie
 * @date 2023/11/27 23:23
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeaFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (beanDefinitionReader != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 此方法是为了从入口上下文类，获取配置信息的地址描述
     *
     * @return
     */
    protected abstract String[] getConfigLocations();
}
