package com.github.pumahawk.apigateway.itf.configurations;

import java.io.InputStream;

public interface SourceConfiguration {
    public String type();
    public InputStream getStream();
}
