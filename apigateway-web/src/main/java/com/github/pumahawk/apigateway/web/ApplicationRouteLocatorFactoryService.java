package com.github.pumahawk.apigateway.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationRouteLocatorFactoryService {

    @Autowired
    RouteLocatorBuilder builder;

    @Autowired
    ApplicationServiceCoreJar jar;

    public RouteLocator generateRouteLocator() {
        Builder routes = builder.routes();

        jar
            .getLoader()
            .stream()
            .flatMap(l -> {
                return l.getSourceConfigurations().stream();
            })
            .map(c -> {
                return jar
                    .getReader()
                    .stream()
                    .filter(r -> r.support(c))
                    .findAny()
                    .map(r -> r.load(c));
            })
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(c -> {
                jar
                    .getSolver()
                    .stream()
                    .filter(s -> s.support(c.type()))
                    .findAny()
                    .ifPresent(s -> routes.route(builder -> s.solve(builder, c)));
            });

        return routes.build();
    }
}
