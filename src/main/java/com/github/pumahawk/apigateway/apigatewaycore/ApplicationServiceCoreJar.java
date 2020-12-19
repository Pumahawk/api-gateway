package com.github.pumahawk.apigateway.apigatewaycore;

import java.util.List;
import java.util.stream.Collectors;

import com.github.pumahawk.apigateway.apigatewaycore.configurations.LoaderConfiguration;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.ReaderConfiguration;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.SolverConfiguration;

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
                var loader = (LoaderConfiguration) Class.forName(c.getClassName()).getDeclaredConstructor(LoaderConfiguration.class).newInstance();
                loader.initConfiguration(c.getProperties());
                return loader;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        readers = applicationConfigurationModel.getLoaders().stream().map(c -> {
            try {
                var readers = (ReaderConfiguration) Class.forName(c.getClassName()).getDeclaredConstructor(ReaderConfiguration.class).newInstance();
                readers.initConfiguration(c.getProperties());
                return readers;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        solvers = applicationConfigurationModel.getLoaders().stream().map(c -> {
            try {
                var solvers = (SolverConfiguration) Class.forName(c.getClassName()).getDeclaredConstructor(SolverConfiguration.class).newInstance();
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
