package com.jie.spring.aop;

/**
 * Filter that restricts matching of a pointcut or introduction to a given set of target classes.
 * <p>
 * 定义类匹配类。用于切点找到给定的接口和目标类
 *
 * @author jie
 * @date 2023/12/24 22:10
 */
public interface ClassFilter {
    /**
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     * @return whether the pointcut should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}
