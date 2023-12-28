package com.jie.spring.aop;

import java.lang.reflect.Method;

/**
 * Advice invoked before a method is invoked. Such advices cannot
 * prevent the method call proceeding, unless they throw a Throwable.
 *
 * @author jie
 * @date 2023/12/27 22:46
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
    /**
     * Callback before a given method is invoked.
     *
     * @param method the method being invoked
     * @param args   the arguments to the method
     * @param target the target of the method invocation. May be {@code null}.
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
