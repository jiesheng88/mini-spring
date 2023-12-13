package com.jie.spring.beans.factory;

/**
 * Interface to be implemented by beans that need to react once all their
 * properties have been set by a BeanFactory: for example, to perform custom
 * initialization, or merely to check that all mandatory properties have been set.
 * <p>
 * 实现此接口的 Bean 对象，会在 BeanFactory 设置属性后作出相应的处理，如：执行自定义初始化，或者仅仅检查是否设置了所有强制属性。
 * 对外提供的初始化 Bean 的接口
 *
 * @author jie
 * @date 2023/12/13 22:25
 */
public interface InitializingBean {
    /**
     * Bean 处理属性填充后调用
     *
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
