package com.jie.spring.core.io;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author jie
 * @date 2023/11/26 17:11
 */
public class DefaultResourceLoaderTest {
    DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testGetResource_succeed_classpath() throws Exception {
        String location = "classpath:important.properties";
        Resource resource = resourceLoader.getResource(location);
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void testGetResource_succeed_filePath() throws Exception {
        String location = "src/main/resources/important.properties";
        Resource resource = resourceLoader.getResource(location);
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void testGetResource_succeed_url() throws Exception {
        String location = "https://github.com/fuzhengwei/small-spring/blob/main/important.properties";
        Resource resource = resourceLoader.getResource(location);
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
