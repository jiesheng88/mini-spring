package com.jie.spring.beans.factory.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Support base class for singleton registries which need to handle {@link FactoryBean} instances,
 * integrated with {@link DefaultSingletonBeanRegistry}'s singleton management.
 * FactoryBean 注册服务
 * 主要作用是关于 FactoryBean 此类对象的注册操作
 *
 * @author jie
 * @date 2023/12/18 22:44
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     * FactoryBeans 创建的单例对象的缓存
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCacheObjectFormFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, object != null ? object : NULL_OBJECT);
            }
            return object != null ? object : NULL_OBJECT;
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(FactoryBean factory, String beanName) {
        try {
            // 获取对象，是由 FactoryBean 接口的实现类定义的
            return factory.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean throw exception on object[" + beanName + "] creation", e);
        }
    }


}
