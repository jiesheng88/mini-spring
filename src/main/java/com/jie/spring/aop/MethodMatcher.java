package com.jie.spring.aop;

import java.lang.reflect.Method;

/**
 * Part of a {@link Pointcut}: Checks whether the target method is eligible for advice.
 * <p>
 * 方法匹配，找到表达式范围内的匹配下的目标类和方法
 *
 * @author jie
 * @date 2023/12/24 22:13
 */
public interface MethodMatcher {
    /**
     * Perform static checking whether the given method matches.
     *
     * @param method
     * @param targetClass
     * @return whether this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
