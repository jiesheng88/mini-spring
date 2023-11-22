package com.jie.spring.beans.factory.config;

import com.jie.spring.beans.PropertyValues;

import java.util.Objects;

/**
 * BeanDefinition 实例保存bean信息，包括：
 * class 类型
 * 方法构造参数
 * bean 属性
 * bean 的scope
 * 。。。
 * <p>
 * 此处简化只包含class类型和bean属性
 */
public class BeanDefinition {

    /**
     * bean class 类，把 Bean 的实例化操作放到容器中处理
     */
    private Class beanClass;

    /**
     * bean class 属性值
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
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
