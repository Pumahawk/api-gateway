package com.github.pumahawk.apigateway;

import java.io.File;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class ApigatewayCoreApplication {

	@Value("${fileConfigurationPath:gateway.json}")
	String fileConfigurationPath;

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayCoreApplication.class, args);
	}

	@Bean
	public ApplicationConfigurationModel applicationConfigurationModel() throws Exception {
		File f = new File(fileConfigurationPath);
		if(f.exists()) {
			ObjectMapper om = new ObjectMapper();
			return om.readValue(f, ApplicationConfigurationModel.class);
		} else {
			return applicationConfigurationModelDefault();
		}
	}


	public ApplicationConfigurationModel applicationConfigurationModelDefault() {
		ApplicationConfigurationModel c = new ApplicationConfigurationModel();

		c.setLoaders(new LinkedList<>());
		c.setReaders(new LinkedList<>());
		c.setSolvers(new LinkedList<>());

		return c;
	}

	@Bean
	RouteLocator routes(ApplicationRouteLocatorFactoryService factory) {
		return factory.generateRouteLocator();
	}


}
