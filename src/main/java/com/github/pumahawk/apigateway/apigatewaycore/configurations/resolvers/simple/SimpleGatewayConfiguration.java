
package com.github.pumahawk.apigateway.apigatewaycore.configurations.resolvers.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "alwaysTrue",
    "method",
    "host",
    "path",
    "remoteAddr",
    "filter",
    "uri"
})
public class SimpleGatewayConfiguration {

    @JsonProperty("type")
    private String type;
    @JsonProperty("alwaysTrue")
    private Boolean alwaysTrue;
    @JsonProperty("method")
    private String method;
    @JsonProperty("host")
    private List<String> host = null;
    @JsonProperty("path")
    private List<String> path = null;
    @JsonProperty("remoteAddr")
    private List<String> remoteAddr = null;
    @JsonProperty("filter")
    private Filter filter;
    @JsonProperty("uri")
    private String uri;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("alwaysTrue")
    public Boolean getAlwaysTrue() {
        return alwaysTrue;
    }

    @JsonProperty("alwaysTrue")
    public void setAlwaysTrue(Boolean alwaysTrue) {
        this.alwaysTrue = alwaysTrue;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("host")
    public List<String> getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(List<String> host) {
        this.host = host;
    }

    @JsonProperty("path")
    public List<String> getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(List<String> path) {
        this.path = path;
    }

    @JsonProperty("remoteAddr")
    public List<String> getRemoteAddr() {
        return remoteAddr;
    }

    @JsonProperty("remoteAddr")
    public void setRemoteAddr(List<String> remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    @JsonProperty("filter")
    public Filter getFilter() {
        return filter;
    }

    @JsonProperty("filter")
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
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
