package com.jie.spring.beans.factory;

/**
 * @author jie
 * @date 2023/11/21 23:06
 */
public interface FactoryBean<T> {

    T getObject();

    boolean isSingleton();
}
