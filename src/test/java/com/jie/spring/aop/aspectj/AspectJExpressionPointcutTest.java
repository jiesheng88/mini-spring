package com.jie.spring.aop.aspectj;


import com.jie.spring.aop.MethodMatcher;
import com.jie.spring.aop.framework.ReflectiveMethodInvocation;
import com.jie.spring.test.aop.AopProxyIUserService;
import com.jie.spring.test.aop.AopProxyUserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jie
 * @date 2023/12/24 22:30
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void test_pointcutExpression() throws NoSuchMethodException {
        String expression = "execution(* com.jie.spring.test.aop.AopProxyIUserService.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(expression);
        Class<AopProxyIUserService> clazz = AopProxyIUserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    /**
     * 案例中可以看到有：
     * 代理的实现 Proxy.newProxyInstance，
     * 方法的匹配 MethodMatcher，
     * 反射的调用 invoke(Object proxy, Method method, Object[] args)，
     * 用户自己拦截方法后的操作。
     * 这样一看其实和我们使用的 AOP 就非常类似了，只不过在使用 AOP 的时候是框架已经提供更好的功能，这里是把所有的核心过程展示出来了。
     */
    @Test
    public void test_proxy_method() {
        // 目标对象（可以替换成任何目标对象）
        // AopProxyUserService 是 AopProxyIUserService 接口的实现类
        Object targetObj = new AopProxyUserService();

        // AOP 代理
        AopProxyIUserService proxy = (AopProxyIUserService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                targetObj.getClass().getInterfaces(), new InvocationHandler() {
                    // 方法匹配器
                    String expression = "execution(* com.jie.spring.test.aop.AopProxyIUserService.*(..))";
                    MethodMatcher methodMatcher = new AspectJExpressionPointcut(expression);

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (methodMatcher.matches(method, targetObj.getClass())) {
                            // 方法拦截器
                            MethodInterceptor methodInterceptor = invocation -> {
                                long start = System.currentTimeMillis();
                                try {
                                    return invocation.proceed();
                                } finally {
                                    System.out.println("监控 - Begin By AOP");
                                    System.out.println("方法名称：" + invocation.getMethod().getName());
                                    System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                                    System.out.println("监控 - End\r\n");
                                }
                            };
                            // 反射调用
                            return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                        }
                        return method.invoke(targetObj, args);
                    }
                });
        String result = proxy.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
