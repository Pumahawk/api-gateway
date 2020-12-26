package com.github.pumahawk.apigateway.apigatewaycore;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pumahawk.apigateway.itf.configurations.GatewayConfiguration;

public class ConfiguratorMock {

    ObjectMapper om = new ObjectMapper();

    public GatewayConfiguration mockConfig(String type, String jsonConfig) throws JsonMappingException, JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        GatewayConfiguration gc = mock(GatewayConfiguration.class);
        TreeNode treeNodeConfig = om.readTree(jsonConfig);
        when(gc.type()).thenReturn(type);
        when(gc.configuration()).thenReturn(treeNodeConfig);
        return gc;
    }
}
