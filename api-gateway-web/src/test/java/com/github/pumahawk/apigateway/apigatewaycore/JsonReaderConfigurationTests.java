package com.github.pumahawk.apigateway.apigatewaycore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

import com.fasterxml.jackson.databind.node.ValueNode;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.GatewayConfiguration;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.JsonReaderConfiguration;
import com.github.pumahawk.apigateway.apigatewaycore.configurations.SourceConfiguration;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class JsonReaderConfigurationTests {

    public SourceConfiguration getJsonSourceConfiguration(String configuration) {
        SourceConfiguration conf = Mockito.mock(SourceConfiguration.class);
        Mockito.when(conf.type()).thenReturn("json");
        Mockito.when(conf.getStream()).thenReturn(new ByteArrayInputStream(configuration.getBytes()));
        return conf;
    }

    @Test
    public void loadSimpleConfiguration() {
        String configuration = "{"
            + "\"type\":\"simple\","
            + "\"uri\":\"http://localhost:8080\""
            + "}";

            JsonReaderConfiguration l = new JsonReaderConfiguration();
            SourceConfiguration c = getJsonSourceConfiguration(configuration);

            GatewayConfiguration  gatewayConf = l.load(c);

        assertEquals("simple", gatewayConf.type());
        assertEquals("simple", ((ValueNode) gatewayConf.configuration().get("type")).asText());
        assertEquals("http://localhost:8080", ((ValueNode) gatewayConf.configuration().get("uri")).asText());
    }
}
