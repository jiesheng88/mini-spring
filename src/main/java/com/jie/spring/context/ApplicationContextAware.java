package com.jie.spring.context;

import com.jie.spring.beans.BeansException;
import com.jie.spring.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 * <p>
 * 实现此接口，既能感知到所属的 ApplicationContext
 *
 * @author jie
 * @date 2023/12/14 22:29
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
