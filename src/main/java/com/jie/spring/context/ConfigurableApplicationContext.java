package com.jie.spring.context;

import com.jie.spring.beans.BeansException;

/**
 * SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link ApplicationContext} interface.
 * <p>
 * 提供refresh这个核心方法，需要在实现中完成刷新容器的操作
 *
 * @author jie
 * @date 2023/11/27 22:52
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 注册虚拟机关闭钩子（注册调用销毁方法）
     */
    void registerShutdownHook();

    /**
     * 手动执行关闭的方法
     */
    void close();
}
