package com.github.pumahawk.apigateway.apigatewaycore.configurations;

public class InvalidConfiguration extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public InvalidConfiguration(String message) {
        super(message);
    }
}
