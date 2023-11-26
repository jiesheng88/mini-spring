package com.jie.spring.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author jie
 * @date 2023/11/26 16:32
 */
public class FileSystemResource implements Resource {
    private File file;
    private String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(this.file.toPath());
    }

    public String getPath() {
        return path;
    }
}
