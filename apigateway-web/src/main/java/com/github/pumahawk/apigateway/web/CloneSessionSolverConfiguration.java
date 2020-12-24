package com.github.pumahawk.apigateway.web;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pumahawk.apigateway.itf.configurations.GatewayConfiguration;
import com.github.pumahawk.apigateway.itf.configurations.SolverConfiguration;
import com.github.pumahawk.apigateway.web.resolvers.clonesession.CloneSessionConfiguration;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class CloneSessionSolverConfiguration implements SolverConfiguration {

    private CloneSessionConfiguration conf;

    @Override
    public boolean support(String type) {
        return "clone-session".equals(type);
    }

    @Override
    public void initConfiguration(TreeNode customConfiguration) {
    }

    @Override
    public Buildable<Route> solve(PredicateSpec builder, GatewayConfiguration gatewayConfiguration) {

        ObjectMapper om = new ObjectMapper();
        conf = om.convertValue(gatewayConfiguration.configuration(), CloneSessionConfiguration.class);

        builder.alwaysTrue();

        builder.alwaysTrue().filters(f -> f.filter(new ClonseSessionGatewayFilter()));
        return builder.uri(getUriFromConfiguration());
    }

    private Optional<String> getGuestTokenNameFromConfiguration() {
        return Optional.ofNullable(conf).map(c -> c.getGuestToken().getName());
    }

    private Optional<String> getGuestTokenValueFromConfiguration() {
        return Optional.ofNullable(conf).map(c -> c.getGuestToken().getValue());
    }

    private String getUriFromConfiguration() {
        return Optional.ofNullable(conf).map(c -> c.getUri())
                .orElseThrow(() -> new RuntimeException("Unable to get uri from clone-session configuration"));
    }
    private URI getURIFromConfiguration() {
        try {
            return new URI(getUriFromConfiguration());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private class ClonseSessionGatewayFilter implements GatewayFilter {

        CookieManager cookieManager = new CookieManager(null, (u, coo) -> (getURIFromConfiguration().getHost() == null || u.getHost() == null) ? false : getURIFromConfiguration().getHost().equals(u.getHost()));

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    
            boolean fromGuess = requestFromGuess(exchange);

            if (fromGuess) {
                setCookiesFromStore(exchange);
            }

            return chain.filter(exchange).doFinally(s -> {
                if (!fromGuess) {
                    extractCookieFromResponse(exchange);
                }
            });
        }

        private void extractCookieFromResponse(ServerWebExchange exchange) {
            HttpHeaders headers = exchange
                .getResponse()
                .getHeaders();
            Map<String, List<String>> responseHeaders = new HashMap<>();
            headers.forEach((h, l) -> responseHeaders.put(h, l));
            try {
                cookieManager.put(getURIFromConfiguration(), responseHeaders);
            } catch (IOException e) {
            }
        }

        private boolean requestFromGuess(ServerWebExchange exchange) {
            Optional<String> hn = getGuestTokenNameFromConfiguration();
            Optional<String> hv = getGuestTokenValueFromConfiguration();
            return 
            hn.flatMap(headerName -> 
            hv.flatMap(headerValue -> 
            Optional
                .of(exchange.getRequest())
                .map(req -> req.getHeaders().get(headerName))
                .filter(l -> !l.isEmpty())
                .map(l -> l.get(0))
            .map(headerValueFound -> headerValue.equals(headerValueFound))))
            .orElse(false);
        }

        private void setCookiesFromStore(ServerWebExchange exchange) {
            exchange.getRequest().mutate().headers(headers -> {
                headers.remove(HttpHeaders.COOKIE);
                headers.put(HttpHeaders.COOKIE, getCookiesFromStore());
            });
            
        }

        private List<String> getCookiesFromStore() {
            return cookieManager
                .getCookieStore()
                .get(getURIFromConfiguration())
                .stream()
                .map(c -> c.toString())
                .collect(Collectors.toList());
        }

    }
    
}
