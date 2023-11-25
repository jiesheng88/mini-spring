package com.jie.spring.beans.factory.config;

/**
 * Bean 的引用
 *
 * @author jie
 * @date 2023/11/25 23:02
 */
public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
