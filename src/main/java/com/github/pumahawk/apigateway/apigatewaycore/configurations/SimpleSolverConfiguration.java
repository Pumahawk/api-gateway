package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.resolvers.simple.SimpleGatewayConfiguration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
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
            .filter(SimpleGatewayConfiguration::getAlwaysTrue)
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
        
        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getAddRequestHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.addRequestHeader(x.getHeaderName(), x.getHeaderValue())));
        
        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getAddRequestParameter())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.addRequestParameter(x.getParam(), x.getValue())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getAddResponseHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.addResponseHeader(x.getHeaderName(), x.getHeaderValue())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getDedupeResponseHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.dedupeResponseHeader(x.getHeaderName(), x.getStrategy())));


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

        public void filter(Function<GatewayFilterSpec, UriSpec> consumer) {
            if (builder instanceof BooleanSpec) {
                ((BooleanSpec) builder).filters(f -> consumer.apply(f));
            }
        }

        public Buildable<Route> uri(String uri) {
            return builder.uri(uri);
        }
    }
    
}
