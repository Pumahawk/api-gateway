package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import java.util.List;

import com.fasterxml.jackson.core.TreeNode;

public interface LoaderConfiguration {
    boolean support(String type);
    void initConfiguration(TreeNode customConfiguration);
    List<SourceConfiguration> getSourceConfigurations();
}
