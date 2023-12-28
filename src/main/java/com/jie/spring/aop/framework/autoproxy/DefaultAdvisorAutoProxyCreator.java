package com.jie.spring.aop.framework.autoproxy;

import com.jie.spring.aop.AdvisedSupport;
import com.jie.spring.aop.Advisor;
import com.jie.spring.aop.ClassFilter;
import com.jie.spring.aop.Pointcut;
import com.jie.spring.aop.TargetSource;
import com.jie.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.jie.spring.aop.framework.ProxyFactory;
import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.BeanFactory;
import com.jie.spring.beans.factory.BeanFactoryAware;
import com.jie.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jie.spring.beans.factory.support.DefaultListableBeaFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @author jie
 * @date 2023/12/27 23:18
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeaFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeaFactory) beanFactory;
    }

    /**
     * Instantiation：实例
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 如果 Bean 是基础 Bean （Advice、Advisor、Pointcut），直接返回
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        // 获取了 advisors 以后就可以遍历相应的 AspectJExpressionPointcutAdvisor 填充对应的属性信息，包括：目标对象、拦截方法、匹配器，之后返回代理对象即可。
        // 那么现在调用方获取到的这个 Bean 对象就是一个已经被切面注入的对象了，当调用方法的时候，则会被按需拦截，处理用户需要的信息
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            // 类匹配
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object potProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
