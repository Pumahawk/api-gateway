package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.UriSpec;


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
        MapperUriSpec mapper = new MapperUriSpec(builder);

        Optional<SimpleGatewayConfiguration> conf = Optional.ofNullable(c);

        
        conf
            .filter(SimpleGatewayConfiguration::isAlwaysTrue)
            .ifPresent(v -> mapper.map(b -> b.alwaysTrue()));
        conf
            .map(SimpleGatewayConfiguration::getMethod)
            .ifPresent(method -> mapper.map(b -> b.method(method)));
        conf
            .map(SimpleGatewayConfiguration::getHost)
            .map(l -> valorizeArr(new String[l.size()], l))
            .ifPresent(pattern -> mapper.map(b -> b.host(pattern)));
        conf
            .map(SimpleGatewayConfiguration::getPath)
            .map(l -> valorizeArr(new String[l.size()], l))
            .ifPresent(pattern -> mapper.map(b -> b.path(pattern)));
        
        conf
            .map(SimpleGatewayConfiguration::getRemoteAddr)
            .map(l -> valorizeArr(new String[l.size()], l))
            .ifPresent(pattern -> mapper.map(b -> b.remoteAddr(pattern)));
        
        return mapper.uri(c.getUri());
    }

    private SimpleGatewayConfiguration getConfiguration(GatewayConfiguration gatewayConfiguration) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(gatewayConfiguration.configuration(), SimpleGatewayConfiguration.class);
    }

    private <T> T[] valorizeArr(T[] arr, List<T> list) {
        int i = 0;
        for (T item : list) {
            arr[i] = item;
            i++;
        }
        return arr;
    }

    public static class SimpleGatewayConfiguration {
        String type;

        boolean alwaysTrue;
        String method;
        List<String> host;
        List<String> path;
        List<String> remoteAddr;
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

        public boolean isAlwaysTrue() {
            return alwaysTrue;
        }

        public void setAlwaysTrue(boolean alwaysTrue) {
            this.alwaysTrue = alwaysTrue;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public List<String> getHost() {
            return host;
        }

        public void setHost(List<String> host) {
            this.host = host;
        }

        public List<String> getPath() {
            return path;
        }

        public void setPath(List<String> path) {
            this.path = path;
        }

        public List<String> getRemoteAddr() {
            return remoteAddr;
        }

        public void setRemoteAddr(List<String> remoteAddr) {
            this.remoteAddr = remoteAddr;
        }
        
    }

    private class MapperUriSpec {

        private UriSpec builder;

        public MapperUriSpec(UriSpec buider) {
            this.builder = buider;
        }

        public void map(Function<PredicateSpec, UriSpec> function) {
            if (builder instanceof PredicateSpec) {
                builder = function.apply((PredicateSpec) builder);
            } else if (builder instanceof BooleanSpec) {
                builder = ((BooleanSpec) builder).and();
                builder = function.apply((PredicateSpec) builder);
            }
        }

        public Buildable<Route> uri(String uri) {
            return builder.uri(uri);
        }
    }
    
}
