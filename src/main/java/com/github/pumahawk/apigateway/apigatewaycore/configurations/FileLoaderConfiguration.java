package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileLoaderConfiguration implements LoaderConfiguration {

    protected File file;
    protected LinkedList<FileSourceConfiguration> list = new LinkedList<>();
    private List<SourceConfiguration> consList;

    @Override
    public void initConfiguration(TreeNode customConfiguration) {
            ObjectMapper om = new ObjectMapper();
            this.consList = getSourceConfigurations(om.convertValue(customConfiguration, PropertyConfigurations.class));
    }

    @Override
    public List<SourceConfiguration> getSourceConfigurations() {
        return consList;
    }

    public List<SourceConfiguration> getSourceConfigurations(PropertyConfigurations customConfiguration) {
        File f = new File(customConfiguration.getLocation());
        instanceFile(f);
        List<SourceConfiguration> list = new LinkedList<>();
        this.list.forEach(list::add);
        return list;
    }

    @Override
    public boolean support(String type) {
        return "file".equals(type);
    }

    private void instanceFile(File file) {
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

    public static class PropertyConfigurations {
        public String location;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

}
