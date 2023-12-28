package com.jie.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Common marker interface for before advice, such as {@link MethodBeforeAdvice}.
 * <p>
 * Advice：拦截器链，都是通过方法拦截器 MethodInterceptor 实现的。
 * 环绕 Advice 类似一个拦截器的链路，BeforeAdvice、AfterAdvice 等。
 *
 * @author jie
 * @date 2023/12/27 22:38
 */
public interface BeforeAdvice extends Advice {

}
