package com.jie.spring.context;

import com.jie.spring.beans.BeansException;

/**
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
}
