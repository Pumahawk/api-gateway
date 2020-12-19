package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

public interface SolverConfiguration {

    public Route.AsyncBuilder solve(PredicateSpec builder);
}
