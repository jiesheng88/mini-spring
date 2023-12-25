package com.jie.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Base class for AOP proxy configuration managers.
 * These are not themselves AOP proxies, but subclasses of this class are
 * normally factories from which AOP proxy instances are obtained directly.
 * <p>
 * <p>
 * 包装切面通知信息：主要是用于将代理、拦截、匹配的各项属性包装到一个类中，方便在 Proxy 实现类进行使用
 * 由外部调用者自行组装通知信息
 *
 * @author jie
 * @date 2023/12/24 22:45
 */
public class AdvisedSupport {
    /**
     * 被代理的目标对象：在目标对象类中提供 Object 入参属性，以及获取目标类信息
     */
    private TargetSource targetSource;
    /**
     * 方法拦截器：由用户自己实现 MethodInterceptor#invoke 方法，做具体的处理
     */
    private MethodInterceptor methodInterceptor;
    /**
     * 方法匹配器（检查目标方法是否符合通知条件）：这个对象由 AspectJExpressionPointcut 提供服务
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
