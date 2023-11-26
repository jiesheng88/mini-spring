package com.jie.spring.beans.factory.config;

import com.jie.spring.beans.factory.BeanFactory;

/**
 * Extension of the {@link BeanFactory}
 * interface to be implemented by bean factories that are capable of
 * autowiring, provided that they want to expose this functionality for
 * existing bean instances.
 * <p>
 * 是一个自动化处理Bean工厂配置的接口是一个自动化处理Bean工厂配置的接口
 *
 * @author jie
 * @date 2023/11/26 21:11
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
}
