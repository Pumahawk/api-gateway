package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderConfiguration implements ReaderConfiguration {

    @Override
    public boolean support(SourceConfiguration source) {
        return String.valueOf(source.type()).toLowerCase().equals("json");
    }

    @Override
    public GatewayConfiguration load(SourceConfiguration source) {
        return new JsonGatewayConfiguration(source);
    }

    private class JsonGatewayConfiguration implements GatewayConfiguration {
        private final ObjectMapper om;
        private final JsonNode root;

        private String uri;

        public JsonGatewayConfiguration(SourceConfiguration source) {
            this.om = new ObjectMapper();
            try {
                root = om.readTree(source.getStream());
                loadConfiguration();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void loadConfiguration() {
            JsonNode uriNode = root.get("uri");
            if (uriNode.isTextual()) {
                uri = uriNode.textValue();
            } else {
                throw new InvalidConfiguration("invalid format property uri");
            }
        }

        @Override
        public String uri() {
            return uri;
        }

    }
}
