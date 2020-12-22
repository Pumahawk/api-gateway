
package com.github.pumahawk.apigateway.apigatewaycore.configurations.resolvers.simple;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "stripVersionMode",
    "locationHeaderName",
    "hostValue",
    "protocolsRegex"
})
public class RewriteLocationResponseHeader {

    @JsonProperty("stripVersionMode")
    private String stripVersionMode;
    @JsonProperty("locationHeaderName")
    private String locationHeaderName;
    @JsonProperty("hostValue")
    private String hostValue;
    @JsonProperty("protocolsRegex")
    private String protocolsRegex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("stripVersionMode")
    public String getStripVersionMode() {
        return stripVersionMode;
    }

    @JsonProperty("stripVersionMode")
    public void setStripVersionMode(String stripVersionMode) {
        this.stripVersionMode = stripVersionMode;
    }

    @JsonProperty("locationHeaderName")
    public String getLocationHeaderName() {
        return locationHeaderName;
    }

    @JsonProperty("locationHeaderName")
    public void setLocationHeaderName(String locationHeaderName) {
        this.locationHeaderName = locationHeaderName;
    }

    @JsonProperty("hostValue")
    public String getHostValue() {
        return hostValue;
    }

    @JsonProperty("hostValue")
    public void setHostValue(String hostValue) {
        this.hostValue = hostValue;
    }

    @JsonProperty("protocolsRegex")
    public String getProtocolsRegex() {
        return protocolsRegex;
    }

    @JsonProperty("protocolsRegex")
    public void setProtocolsRegex(String protocolsRegex) {
        this.protocolsRegex = protocolsRegex;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
