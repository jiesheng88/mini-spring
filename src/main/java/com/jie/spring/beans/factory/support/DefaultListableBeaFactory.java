package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.ConfigurableListableBeanFactory;
import com.jie.spring.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心类
 * 继承AbstractAutowireCapableBeanFactory，也就具备了接口 BeanFactory 和 AbstractBeanFactory 等一连串的功能实现。（获取bean定义）
 * 实现BeanDefinitionRegistry接口的方法，就可以注册bean定义
 * 接口定义了注册，抽象类定义了获取
 *
 * @author jie
 * @date 2023/11/22 23:10
 */
public class DefaultListableBeaFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanNme, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanNme, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
//        for (String beanName : beanDefinitionMap.keySet()) {
//            getBean(beanName);
//        }
    }
}
