package com.jie.spring.util;

/**
 * @author jie
 * @date 2023/11/26 16:40
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (classLoader == null) {
            // No thread context class loader -> use class loader of this class.
            classLoader = ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }
}
