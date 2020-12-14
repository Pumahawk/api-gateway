package com.github.pumahawk.apigateway.apigatewaycore.configurations;

public interface ReaderConfiguration {
    public boolean support(SourceConfiguration source);
    public GatewayConfiguration load(SourceConfiguration source);
}
