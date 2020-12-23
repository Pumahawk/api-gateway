
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
    "headerName",
    "regex",
    "replacement"
})
public class RewriteResponseHeader {

    @JsonProperty("headerName")
    private String headerName;
    @JsonProperty("regex")
    private String regex;
    @JsonProperty("replacement")
    private String replacement;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("headerName")
    public String getHeaderName() {
        return headerName;
    }

    @JsonProperty("headerName")
    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    @JsonProperty("regex")
    public String getRegex() {
        return regex;
    }

    @JsonProperty("regex")
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @JsonProperty("replacement")
    public String getReplacement() {
        return replacement;
    }

    @JsonProperty("replacement")
    public void setReplacement(String replacement) {
        this.replacement = replacement;
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
