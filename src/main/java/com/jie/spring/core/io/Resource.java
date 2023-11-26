package com.jie.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * io包主要用于处理资源加载流
 *
 * @author jie
 * @date 2023/11/26 16:28
 */
public interface Resource {
    /**
     * 提供获取InputStream流的方法，实现类中分别实现三种不同文件的流文件操作：classPth、FileSystem、URL
     *
     * @return
     */
    public InputStream getInputStream() throws IOException;
}
