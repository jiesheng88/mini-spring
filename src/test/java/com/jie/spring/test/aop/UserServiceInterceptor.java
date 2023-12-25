package com.jie.spring.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 用户自定义的拦截方法需要实现 MethodInterceptor 接口的 invoke 方法，
 * 使用方式与 Spring AOP 非常相似，也是包装 invocation.proceed() 放行，并在 finally 中添加监控信息。
 *
 * @author jie
 * @date 2023/12/25 22:30
 */
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("监控 - Begin By AOP");
            System.out.println("方法名称：" + methodInvocation.getMethod());
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End\r\n");
        }
    }
}
