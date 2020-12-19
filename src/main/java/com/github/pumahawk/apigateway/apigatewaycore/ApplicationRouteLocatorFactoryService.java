package com.github.pumahawk.apigateway.apigatewaycore;

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
            .map(c -> {
                return jar
                    .getSolver()
                    .stream()
                    .filter(s -> s.support(c.type()))
                    .findAny();
            })
            .filter(Optional::isPresent)
            .map(Optional::get)
            .forEach(s -> routes.route(builder -> s.solve(builder)));

        return routes.build();
    }
}
