package com.github.pumahawk.apigateway.web;

import java.util.List;
import java.util.stream.Collectors;

import com.github.pumahawk.apigateway.itf.configurations.LoaderConfiguration;
import com.github.pumahawk.apigateway.itf.configurations.ReaderConfiguration;
import com.github.pumahawk.apigateway.itf.configurations.SolverConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceCoreJar {

    final List<LoaderConfiguration> loaders;
    final List<ReaderConfiguration> readers;
    final List<SolverConfiguration> solvers;

    @Autowired
    private ApplicationServiceCoreJar(ApplicationConfigurationModel applicationConfigurationModel) {

        loaders = applicationConfigurationModel.getLoaders().stream().map(c -> {
            try {
                LoaderConfiguration loader = (LoaderConfiguration) Class.forName(c.getClassName(), true, Thread.currentThread().getContextClassLoader()).getConstructor().newInstance();
                loader.initConfiguration(c.getProperties());
                return loader;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        readers = applicationConfigurationModel.getReader().stream().map(c -> {
            try {
                ReaderConfiguration readers = (ReaderConfiguration) Class.forName(c.getClassName(), true, Thread.currentThread().getContextClassLoader()).getConstructor().newInstance();
                readers.initConfiguration(c.getProperties());
                return readers;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        solvers = applicationConfigurationModel.getSolvers().stream().map(c -> {
            try {
                SolverConfiguration solvers = (SolverConfiguration) Class.forName(c.getClassName(), true, Thread.currentThread().getContextClassLoader()).getConstructor().newInstance();
                solvers.initConfiguration(c.getProperties());
                return solvers;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

    }

	public List<LoaderConfiguration> getLoader() {
        return loaders;
    }
	public List<ReaderConfiguration> getReader() {
        return readers;
    }
	public List<SolverConfiguration> getSolver() {
        return solvers;
    }
}
