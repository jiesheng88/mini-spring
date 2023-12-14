package com.jie.spring.context.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.config.BeanPostProcessor;
import com.jie.spring.context.ApplicationContext;
import com.jie.spring.context.ApplicationContextAware;

/**
 * 包装处理器
 * 由于 ApplicationContext 的获取并不能直接在创建 Bean 时候就可以拿到，所以需要在 refresh 操作时，
 * 把 ApplicationContext 写入到一个包装的 BeanPostProcessor 中去，
 * 再由 AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization 方法调用。
 *
 * @author jie
 * @date 2023/12/14 22:34
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object potProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
