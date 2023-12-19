package com.jie.spring.test;

import com.jie.spring.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jie
 * @date 2023/12/18 23:18
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    /**
     * 模拟了 UserDao 的原有功能，类似于 MyBatis 框架中的代理操作
     * getObject() 提供的就是一个 InvocationHandler 的代理对象，当有方法调用时，则执行代理对象的功能
     *
     * @return
     */
    @Override
    public IUserDao getObject() {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "jack");
            hashMap.put("10002", "mary");
            hashMap.put("10003", "jie");

            return "你被代理了 " + method.getName() + ": " + hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
