package com.github.pumahawk.apigateway.apigatewaycore.configurations;

import com.fasterxml.jackson.core.TreeNode;

public interface GatewayConfiguration {
    String type();
    TreeNode configuration();
}
