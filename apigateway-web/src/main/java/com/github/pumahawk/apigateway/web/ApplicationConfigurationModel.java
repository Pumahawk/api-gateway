package com.github.pumahawk.apigateway.web;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class ApplicationConfigurationModel {

    List<ClassBasicConfiguration> loaders = new LinkedList<>();
    List<ClassBasicConfiguration> readers = new LinkedList<>();
    List<ClassBasicConfiguration> solvers = new LinkedList<>();

    public static class ClassBasicConfiguration {
        String className;
        JsonNode properties;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public JsonNode getProperties() {
            return properties;
        }

        public void setProperties(JsonNode properties) {
            this.properties = properties;
        }
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
