package com.github.pumahawk.apigateway.processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.github.pumahawk.apigateway.itf.configurations.SourceConfiguration;

public class FileSourceConfiguration implements SourceConfiguration {
    protected File file;

    public FileSourceConfiguration(File file) {
        this.file = file;
    }

    @Override
    public String type() {
        String filename = file.getName();
        if (filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            return "none";
        }
    }

    @Override
    public InputStream getStream() {
        try {
            return new FileInputStream(this.file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
