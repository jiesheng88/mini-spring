package com.jie.spring.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取资源的具体实现，保证外部调用方仅关心具体调用结果。
 *
 * @author jie
 * @date 2023/11/26 17:01
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location mst not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // classpath 下的资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 尝试当成url来处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 出错时，当成文件系统下的资源处理
                return new FileSystemResource(location);
            }

        }
    }
}
