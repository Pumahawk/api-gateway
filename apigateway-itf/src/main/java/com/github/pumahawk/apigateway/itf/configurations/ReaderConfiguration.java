package com.github.pumahawk.apigateway.itf.configurations;

import com.fasterxml.jackson.core.TreeNode;

public interface ReaderConfiguration {
    public boolean support(SourceConfiguration source);
    void initConfiguration(TreeNode customConfiguration);
    public GatewayConfiguration load(SourceConfiguration source);
}
