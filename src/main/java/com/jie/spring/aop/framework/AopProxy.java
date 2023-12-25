package com.jie.spring.aop.framework;

/**
 * Delegate interface for a configured AOP proxy, allowing for the creation
 * of actual proxy objects.
 *
 * <p>Out-of-the-box implementations are available for JDK dynamic proxies
 * and for CGLIB proxies, as applied by DefaultAopProxyFactory
 * <p>
 * 定义一个标准接口，用于获取代理类，因为具体的代理方式有 JDK 方法，或 Cglib 方法
 *
 * @author jie
 * @date 2023/12/24 22:59
 */
public interface AopProxy {

    Object getProxy();
}
