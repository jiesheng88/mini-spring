package com.jie.spring.beans.factory;

/**
 * Callback that allows a bean to be aware of the bean {@link ClassLoader class loader};
 * that is, the class loader used by the present bean factory to load bean classes.
 * <p>
 * 实现此接口，既能感知到所属的 ClassLoader
 *
 * @author jie
 * @date 2023/12/14 22:24
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
