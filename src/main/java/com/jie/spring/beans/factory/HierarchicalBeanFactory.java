package com.jie.spring.beans.factory;

/**
 * Sub-interface implemented by bean factories that can be part of a hierarchy.
 * <p>
 * 在 Spring 源码中它提供了可以获取父类 BeanFactory 方法，属于是一种扩展工厂的层次子接口。
 *
 * @author jie
 * @date 2023/11/26 21:09
 */
public interface HierarchicalBeanFactory extends BeanFactory {
}
