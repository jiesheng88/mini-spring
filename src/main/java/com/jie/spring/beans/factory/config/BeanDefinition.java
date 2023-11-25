package com.jie.spring.beans.factory.config;

import com.jie.spring.beans.PropertyValues;

import java.util.Objects;

/**
 * BeanDefinition 实例保存bean信息，包括：
 * class 类型
 * bean属性信息
 */
public class BeanDefinition {
    /**
     * bean class 类，把 Bean 的实例化操作放到容器中处理
     */
    private Class beanClass;
    /**
     * bean属性信息，一个类中属性信息有可能很多，需要使用一个集合包装下
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
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
