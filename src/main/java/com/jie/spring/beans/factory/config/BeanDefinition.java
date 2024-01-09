package com.jie.spring.beans.factory.config;

import com.jie.spring.beans.PropertyValues;

import java.util.Objects;

/**
 * BeanDefinition 实例保存bean信息，包括：
 * class 类型
 * bean属性信息
 * 初始化Bean的方法名
 * 销毁Bean的方法名
 * Bean 的作用范围
 *
 * @author jie
 */
public class BeanDefinition {
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    /**
     * bean class 类，把 Bean 的实例化操作放到容器中处理
     */
    private Class<?> beanClass;
    /**
     * bean属性信息，一个类中属性信息有可能很多，需要使用一个集合包装下
     */
    private PropertyValues propertyValues;
    /**
     * 在 spring.xml 中配置的初始化Bean的方法名
     */
    private String initMethodName;
    /**
     * 在 spring.xml 中配置的销毁Bean的方法名
     */
    private String destroyMethodName;

    private String scope = SCOPE_SINGLETON;
    /**
     * 两个属性，用于把从 spring.xml 中解析到的 Bean 对象作用范围填充到属性中
     */
    private boolean singleton = true;
    private boolean prototype = true;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    // 在 XML 注册 Bean 定义时，通过scope字段来判断是单例还是原型
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public String getScope() {
        return scope;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
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
