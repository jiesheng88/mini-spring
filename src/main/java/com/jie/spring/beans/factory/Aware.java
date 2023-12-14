package com.jie.spring.beans.factory;

/**
 * Marker superinterface indicating that a bean is eligible to be
 * notified by the Spring container of a particular framework object
 * through a callback-style method.  Actual method signature is
 * determined by individual subinterfaces, but should typically
 * consist of just one void-returning method that accepts a single argument.
 * <p>
 * 标记类接口，实现该接口可以被Spring容器感知
 * 在 Spring 中有特别多类似这样的标记接口的设计方式，它们的存在就像是一种标签一样，
 * 可以方便统一摘取出属于此类接口的实现类，通常会有 instanceof 一起判断使用。
 *
 * @author jie
 * @date 2023/12/14 22:16
 */
public interface Aware {
}
