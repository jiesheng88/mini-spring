package com.jie.spring.aop.framework;

import com.jie.spring.aop.AdvisedSupport;

/**
 * 代理工厂：主要解决的是关于 JDK 和 Cglib 两种代理选择的问题
 *
 * @author jie
 * @date 2023/12/27 23:10
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
