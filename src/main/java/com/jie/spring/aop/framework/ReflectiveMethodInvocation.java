package com.jie.spring.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * <p>Invokes the target object using reflection. Subclasses can override the
 * #invokeJoinpoint() method to change this behavior, so this is also
 * a useful base class for more specialized MethodInvocation implementations.
 * <p>
 * <p>
 * 保存目标对象、方法、入参的容器，供通过 MethodInvocation 接口获取 Method、入参、反射调用对象。
 * 代理对象创建后，最终的拦截工作都是交给了 MethodInvocation。
 * JDK 交给了 ReflectiveMethodInvocation，而 CGLIB 交给了 CglibMethodInvocation。
 *
 * @author jie
 * @date 2023/12/24 23:05
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    /**
     * 目标对象
     */
    protected final Object target;
    /**
     * 方法
     */
    protected final Method method;
    /**
     * 入参
     */
    protected final Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * 使用反射调用目标对象
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
