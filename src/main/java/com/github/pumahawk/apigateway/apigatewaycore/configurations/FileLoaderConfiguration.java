package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

public class FileLoaderConfiguration implements LoaderConfiguration<FileSourceConfiguration> {

    final protected File file;
    final protected LinkedList<FileSourceConfiguration> list = new LinkedList<>();

    public FileLoaderConfiguration(File file) {
        this.file = file;
        loadFileConfiguration();
    }

    private void loadFileConfiguration() {
        if (!this.file.isDirectory()) {
            list.add(new FileSourceConfiguration(this.file));
        } else {
            for (File c : this.file.listFiles()) {
                if (!c.isDirectory()) {
                    list.add(new FileSourceConfiguration(c));
                }
            }
        }
    }

    @Override
    public Iterator<FileSourceConfiguration> iterator() {
        return list.iterator();
    }

}
