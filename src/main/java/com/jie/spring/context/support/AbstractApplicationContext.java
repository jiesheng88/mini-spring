package com.jie.spring.context.support;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.ConfigurableListableBeanFactory;
import com.jie.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.jie.spring.beans.factory.config.BeanPostProcessor;
import com.jie.spring.context.ApplicationEvent;
import com.jie.spring.context.ApplicationListener;
import com.jie.spring.context.ConfigurableApplicationContext;
import com.jie.spring.context.event.ApplicationEventMulticaster;
import com.jie.spring.context.event.ContextClosedEvent;
import com.jie.spring.context.event.ContextRefreshedEvent;
import com.jie.spring.context.event.SimpleApplicationEventMulticaster;
import com.jie.spring.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * 应用上下文抽象类
 * 继承DefaultResourceLoader，获得了读取资源的能力，是为了处理spring.xml配置资源的加载
 *
 * @author jie
 * @date 2023/11/27 22:55
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * 定义refresh方法的实现过程
     * refresh() 方法就是整个 Spring 容器的操作过程
     *
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // 1、创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();

        // 2、获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 2.1 【增加-注册包装的 BeanPostProcessor】添加 ApplicationContextAwareProcessor，
        // 让继承自 ApplicationContextAware 的 Bean 对象都能感知到所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 3、在Bean实例化之前，注册BeanFactoryPostProcessor
        // Invoke factory processors registered as beans in the context.
        invokeBeanFactoryPostProcessors(beanFactory);

        // 4、BeanPostProcessor 需要提前于其他Bean对象实例化之前执行注册擦嘴
        registerBeanPostProcessors(beanFactory);

        // 6、初始化事件发布者
        initApplicationEventMulticaster();

        // 7、注册事件监听器
        registerListeners();

        // 8. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9、发布容器刷新完成事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        // 初始化一个事件广播器
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        // 发布容器刷新事件
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicasterEvent(event);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 销毁所有配置了销毁方法的Bean
        getBeanFactory().destroySingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }
}
