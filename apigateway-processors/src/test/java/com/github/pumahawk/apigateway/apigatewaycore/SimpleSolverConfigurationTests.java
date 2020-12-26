package com.github.pumahawk.apigateway.apigatewaycore;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.pumahawk.apigateway.itf.configurations.GatewayConfiguration;
import com.github.pumahawk.apigateway.processors.SimpleSolverConfiguration;

import org.junit.jupiter.api.Test;

public class SimpleSolverConfigurationTests {

    ConfiguratorMock configuratorMock = new ConfiguratorMock();

    @Test
    public void execute_alwaysTrue_true() throws JsonMappingException, JsonProcessingException {
        String jsonConfig = "{\"type\": \"simple\", \"alwaysTrue\": true, \"uri\":\"https://httpbin.org\"}";
        GatewayConfiguration gc = configuratorMock.mockConfig("simple", jsonConfig);
        Solver s = new Solver(new SimpleSolverConfiguration());

        s.solve(gc);

        verify(s.getBuilder(), times(1)).alwaysTrue();
    }

    @Test
    public void execute_alwaysTrue_false() throws JsonMappingException, JsonProcessingException {
        String jsonConfig = "{\"type\": \"simple\", \"alwaysTrue\": false, \"uri\":\"https://httpbin.org\"}";
        GatewayConfiguration gc = configuratorMock.mockConfig("simple", jsonConfig);
        Solver s = new Solver(new SimpleSolverConfiguration());

        s.solve(gc);

        verify(s.getBuilder(), times(0)).alwaysTrue();
    }

    @Test
    public void execute_alwaysTrue_null() throws JsonMappingException, JsonProcessingException {
        String jsonConfig = "{\"type\": \"simple\", \"alwaysTrue\": null, \"uri\":\"https://httpbin.org\"}";
        GatewayConfiguration gc = configuratorMock.mockConfig("simple", jsonConfig);
        Solver s = new Solver(new SimpleSolverConfiguration());

        s.solve(gc);

        verify(s.getBuilder(), times(0)).alwaysTrue();
    }
}
