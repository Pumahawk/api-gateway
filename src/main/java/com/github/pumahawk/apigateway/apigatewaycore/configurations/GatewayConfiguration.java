package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import org.springframework.cloud.gateway.route.Route;

public interface GatewayConfiguration {
    public boolean support(SourceConfiguration source);
    public Route.AsyncBuilder load(SourceConfiguration source);
}
