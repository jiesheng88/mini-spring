package com.jie.spring.beans.factory;

/**
 * Bean 容器
 * 1、简单的Bean 容器——BeanFactory，内部包含一个map，用以保存bean，只有注册和获取bean
 * 2、将类改成接口
 */
public interface BeanFactory {
    /**
     * 获取bean
     *
     * @param name
     * @return
     */
    Object getBean(String name);


}
