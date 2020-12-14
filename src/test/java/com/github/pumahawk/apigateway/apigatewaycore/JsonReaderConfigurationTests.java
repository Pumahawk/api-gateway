package com.github.pumahawk.apigateway.apigatewaycore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;

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
        var configuration = "{"
            + "\"uri\":\"http://localhost:8080\""
            + "}";

        var l = new JsonReaderConfiguration();
        var c = getJsonSourceConfiguration(configuration);

        var gatewayConf = l.load(c);

        assertEquals("http://localhost:8080", gatewayConf.uri());
    }
}
