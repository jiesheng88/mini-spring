package com.jie.spring.beans.factory;

import com.jie.spring.beans.BeansException;

/**
 * Bean 容器
 * 1、简单的Bean 容器——BeanFactory，内部包含一个map，用以保存bean，只有注册和获取bean
 * 2、将类改成接口
 */
public interface BeanFactory {
    /**
     * 获取bean（只能获取无参构造）
     *
     * @param name
     * @return
     */
    Object getBean(String name) throws BeansException;

    /**
     * 获取Bean （有参构造）
     *
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;
}
