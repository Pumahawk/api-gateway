package com.github.pumahawk.apigateway.web;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pumahawk.apigateway.itf.configurations.GatewayConfiguration;
import com.github.pumahawk.apigateway.itf.configurations.SolverConfiguration;
import com.github.pumahawk.apigateway.web.resolvers.simple.SimpleGatewayConfiguration;

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
            .map(SimpleGatewayConfiguration::getAlwaysTrue)
            .filter(x -> x)
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

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getPrefixPath())
            .ifPresent(x -> mapper.filter(f -> f.prefixPath(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getPreserveHostHeader())
            .filter(x -> x)
            .ifPresent(x -> mapper.filter(f -> f.preserveHostHeader()));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRedirect())
            .ifPresent(x -> mapper.filter(f -> f.redirect(x.getStatus(), x.getUrl())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRemoveRequestHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.removeRequestHeader(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRemoveRequestParameter())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.removeRequestParameter(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRemoveResponseHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.removeResponseHeader(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRequestHeaderToRequestUri())
            .ifPresent(x -> mapper.filter(f -> f.requestHeaderToRequestUri(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRetry())
            .ifPresent(x -> mapper.filter(f -> f.retry(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRewriteLocationResponseHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.rewriteLocationResponseHeader(x.getStripVersionMode(), x.getLocationHeaderName(), x.getHostValue(), x.getProtocolsRegex())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRewritePath())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.rewritePath(x.getRegex(), x.getReplacement())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getRewriteResponseHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.rewriteResponseHeader(x.getHeaderName(), x.getRegex(), x.getReplacement())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getSaveSession())
            .filter(x -> x)
            .ifPresent(x -> mapper.filter(f -> f.saveSession()));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getSecureHeaders())
            .filter(x -> x)
            .ifPresent(x -> mapper.filter(f -> f.secureHeaders()));

            
        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getSetPath())
            .ifPresent(x -> mapper.filter(f -> f.setPath(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getSetRequestHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.setRequestHeader(x.getHeaderName(), x.getHeaderValue())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getSetRequestSize())
            .ifPresent(x -> mapper.filter(f -> f.setRequestSize(x)));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getSetResponseHeader())
            .map(List::stream)
            .orElseGet(() -> Stream.empty())
            .forEach(x -> mapper.filter(f -> f.setResponseHeader(x.getHeaderName(), x.getHeaderValue())));

        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getSetStatus())
            .ifPresent(x -> mapper.filter(f -> f.setStatus(x)));
            
        conf
            .map(SimpleGatewayConfiguration::getFilter)
            .map(f -> f.getStripPrefix())
            .ifPresent(x -> mapper.filter(f -> f.stripPrefix(x)));

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
