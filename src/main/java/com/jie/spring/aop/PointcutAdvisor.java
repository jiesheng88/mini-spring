package com.jie.spring.aop;

/**
 * PointcutAdvisor 承担了 Pointcut 和 Advisor 的组合。
 * Pointcut 用于获取 JoinPoint
 * Advisor 决定于 JoinPoint 执行什么操作
 *
 * @author jie
 * @date 2023/12/27 22:56
 */
public interface PointcutAdvisor extends Advisor {
    /**
     * Get the Pointcut that drives this advisor.
     *
     * @return
     */
    Pointcut getPointcut();
}
