package com.jie.spring.test.aop;

import com.jie.spring.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 自定义拦截方法
 * 不再是实现 MethodInterceptor 接口，而是实现 MethodBeforeAdvice 环绕拦截
 *
 * @author jie
 * @date 2023/12/28 22:11
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截的方法：" + method.getName());
    }
}
