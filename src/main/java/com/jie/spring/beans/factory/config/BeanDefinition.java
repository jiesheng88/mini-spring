package com.jie.spring.beans.factory.config;

import java.util.Objects;

/**
 * BeanDefinition 实例保存bean信息，包括：
 * class 类型
 */
public class BeanDefinition {

    /**
     * bean class 类，把 Bean 的实例化操作放到容器中处理
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BeanDefinition bd = (BeanDefinition) obj;
        return beanClass.equals(bd);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(beanClass);
    }


}
