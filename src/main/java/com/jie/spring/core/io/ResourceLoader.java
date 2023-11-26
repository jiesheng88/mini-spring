package com.jie.spring.core.io;

/**
 * 统一资源加载器
 *
 * @author jie
 * @date 2023/11/26 16:58
 */
public interface ResourceLoader {
    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
