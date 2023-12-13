package com.jie.spring.beans.factory;

/**
 * Interface to be implemented by beans that want to release resources
 * on destruction. A BeanFactory is supposed to invoke the destroy
 * method if it disposes a cached singleton. An application context
 * is supposed to dispose all of its singletons on close.
 * <p>
 * 对外提供的销毁 Bean 接口
 *
 * @author jie
 * @date 2023/12/13 22:26
 */
public interface DisposableBean {
    /**
     * 销毁 Bean
     *
     * @throws Exception
     */
    void destroy() throws Exception;
}
