package com.github.pumahawk.apigateway.apigatewaycore;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.TreeNode;

public class ApplicationConfigurationModel {

    List<ClassBasicConfiguration> sources = new LinkedList<>();
    List<ClassBasicConfiguration> loaders = new LinkedList<>();
    List<ClassBasicConfiguration> readers = new LinkedList<>();
    List<ClassBasicConfiguration> solvers = new LinkedList<>();

    public static class ClassBasicConfiguration {
        String className;
        TreeNode properties;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public TreeNode getProperties() {
            return properties;
        }

        public void setProperties(TreeNode properties) {
            this.properties = properties;
        }
    }

    public List<ClassBasicConfiguration> getSources() {
        return sources;
    }

    public void setSources(List<ClassBasicConfiguration> sources) {
        this.sources = sources;
    }

    public List<ClassBasicConfiguration> getLoaders() {
        return loaders;
    }

    public void setLoaders(List<ClassBasicConfiguration> loaders) {
        this.loaders = loaders;
    }

    public List<ClassBasicConfiguration> getReader() {
        return readers;
    }

    public void setReaders(List<ClassBasicConfiguration> readers) {
        this.readers = readers;
    }

    public List<ClassBasicConfiguration> getSolvers() {
        return solvers;
    }

    public void setSolvers(List<ClassBasicConfiguration> solvers) {
        this.solvers = solvers;
    }

}
