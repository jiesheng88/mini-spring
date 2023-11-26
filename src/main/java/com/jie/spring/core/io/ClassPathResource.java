package com.jie.spring.core.io;

import com.jie.spring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 通过ClassLoader读取path下的文件信息
 *
 * @author jie
 * @date 2023/11/26 16:29
 */
public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return inputStream;
    }
}
