package com.jie.spring.beans.factory.config;

import com.jie.spring.beans.factory.BeanFactory;
import com.jie.spring.beans.factory.HierarchicalBeanFactory;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link BeanFactory} interface.
 * <p>
 * 可获取 BeanPostProcessor、BeanClassLoader等的一个配置化接口。
 *
 * @author jie
 * @date 2023/11/26 21:13
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

}
