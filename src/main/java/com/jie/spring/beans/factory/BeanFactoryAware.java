package com.jie.spring.beans.factory;

import com.jie.spring.beans.BeansException;

/**
 * Interface to be implemented by beans that wish to be aware of their owning {@link BeanFactory}.
 * <p>
 * 实现此接口，既能感知到所属的 BeanFactory
 *
 * @author jie
 * @date 2023/12/14 22:20
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
