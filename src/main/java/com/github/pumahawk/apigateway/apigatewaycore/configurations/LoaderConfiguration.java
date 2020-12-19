package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import java.util.List;

import com.fasterxml.jackson.core.TreeNode;

public interface LoaderConfiguration<T extends SourceConfiguration> {
    boolean support(String type);
    List<SourceConfiguration> getSourceConfigurations(TreeNode customConfiguration);
}
