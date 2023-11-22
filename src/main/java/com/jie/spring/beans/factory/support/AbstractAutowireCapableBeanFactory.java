package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.InvocationTargetException;

/**
 * 实例化Bean类
 * 实现继承的抽象类中的创建Bean方法
 *
 * @author jie
 * @date 2023/11/21 23:18
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            // Bean 的实例化操作 TODO：有构造函数入参的对象怎么处理？
            Class clazz = beanDefinition.getBeanClass();
            bean = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 存放到单例对象的缓存中去
        addSingleton(beanName, bean);
        return bean;
    }
}
