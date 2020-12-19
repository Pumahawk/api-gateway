package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

public class SimpleSolverConfiguration implements SolverConfiguration {

    @Override
    public boolean support(String type) {
        return "simple".equals(type);
    }

    @Override
    public void initConfiguration(TreeNode customConfiguration) {

    }

    @Override
    public Buildable<Route> solve(PredicateSpec builder, GatewayConfiguration gatewayConfiguration) {
        SimpleGatewayConfiguration c = getConfiguration(gatewayConfiguration);
        return builder.alwaysTrue().uri(c.getUri());
    }

    private SimpleGatewayConfiguration getConfiguration(GatewayConfiguration gatewayConfiguration) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(gatewayConfiguration.configuration(), SimpleGatewayConfiguration.class);
    }

    public static class SimpleGatewayConfiguration {
        String type;
        String uri;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    
}
