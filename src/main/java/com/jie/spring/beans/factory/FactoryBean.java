package com.jie.spring.beans.factory;

/**
 * Interface to be implemented by objects used within a {@link BeanFactory}
 * which are themselves factories. If a bean implements this interface,
 * it is used as a factory for an object to expose, not directly as a bean
 * instance that will be exposed itself.
 *
 * @author jie
 * @date 2023/11/21 23:06
 */
public interface FactoryBean<T> {
    /**
     * 获取对象
     *
     * @return
     */
    T getObject();

    /**
     * 获取对象类型
     *
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否是单例对象，如果单例对象会被放到内存中
     *
     * @return
     */
    boolean isSingleton();
}
