package com.github.pumahawk.apigateway.web;

import java.util.Optional;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pumahawk.apigateway.itf.configurations.GatewayConfiguration;
import com.github.pumahawk.apigateway.itf.configurations.ReaderConfiguration;
import com.github.pumahawk.apigateway.itf.configurations.SourceConfiguration;

public class JsonReaderConfiguration implements ReaderConfiguration {

    @Override
    public void initConfiguration(TreeNode customConfiguration) {
    }

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

        public JsonGatewayConfiguration(SourceConfiguration source) {
            this.om = new ObjectMapper();
            try {
                root = om.readTree(source.getStream());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String type() {
            return Optional
                .of(root)
                .map(r -> r.get("type"))
                .filter(n -> n.isValueNode())
                .map(v -> v.asText())
                .get();
        }

        @Override
        public TreeNode configuration() {
            return root;
        }

    }
}
