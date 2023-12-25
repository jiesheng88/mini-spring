package com.jie.spring.aop.framework;

import com.jie.spring.aop.AdvisedSupport;
import com.jie.spring.aop.TargetSource;
import com.jie.spring.aop.aspectj.AspectJExpressionPointcut;
import com.jie.spring.test.aop.AopProxyIUserService;
import com.jie.spring.test.aop.AopProxyUserService;
import com.jie.spring.test.aop.UserServiceInterceptor;
import org.junit.Test;

/**
 * 整个案例测试了 AOP 在于 Spring 结合前的核心代码，包括什么是目标对象、怎么组装代理信息、如何调用代理对象。
 * AdvisedSupport，包装了目标对象、用户自己实现的拦截方法以及方法匹配表达式。
 * 之后就是分别调用 JdkDynamicAopProxy、Cglib2AopProxy，两个不同方式实现的代理类，看看是否可以成功拦截方法
 *
 * @author jie
 * @date 2023/12/25 22:33
 */
public class AopProxyTest {

    @Test
    public void test_aopProxy() {
        // 目标对象
        AopProxyIUserService userService = new AopProxyUserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        String expression = "execution(* com.jie.spring.test.aop.AopProxyIUserService.*(..))";
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut(expression));

        // 代理对象（JDK）
        AopProxyIUserService jdkUserService =
                (AopProxyIUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + jdkUserService.queryUserInfo());

        // 代理对象（Cglib）
        AopProxyIUserService cglibUserService = (AopProxyIUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + cglibUserService.register("jie"));

    }
}
