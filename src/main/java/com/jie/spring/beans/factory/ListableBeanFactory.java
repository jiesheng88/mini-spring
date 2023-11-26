package com.jie.spring.beans.factory;

import com.jie.spring.beans.BeansException;

import java.util.Map;

/**
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories
 * that can enumerate all their bean instances, rather than attempting bean lookup
 * by name one by one as requested by clients. BeanFactory implementations that
 * preload all their bean definitions (such as XML-based factories) may implement
 * this interface.
 * <p>
 * 是一个拓展接口，新增了getBeansOfType、getBeanDefinitionNames
 * <p>
 * 接口可以进行继承
 *
 * @author jie
 * @date 2023/11/26 21:05
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 根据类型获取Bean
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     * <p>
     * 返回注册表中所有的Bean名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
