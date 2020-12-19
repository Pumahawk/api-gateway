package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import com.fasterxml.jackson.core.TreeNode;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

public interface SolverConfiguration {

    public boolean support(String type);
    void initConfiguration(TreeNode customConfiguration);
    public Route.AsyncBuilder solve(PredicateSpec builder);
}
