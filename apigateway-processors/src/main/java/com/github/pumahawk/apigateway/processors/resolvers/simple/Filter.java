
package com.github.pumahawk.apigateway.web.resolvers.simple;

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
    "addRequestHeader",
    "addRequestParameter",
    "addResponseHeader",
    "dedupeResponseHeader",
    "prefixPath",
    "preserveHostHeader",
    "redirect",
    "removeRequestHeader",
    "removeRequestParameter",
    "removeResponseHeader",
    "requestHeaderToRequestUri",
    "retry",
    "rewriteLocationResponseHeader",
    "rewritePath",
    "rewriteResponseHeader",
    "saveSession",
    "secureHeaders",
    "setPath",
    "setRequestHeader",
    "setRequestSize",
    "setResponseHeader",
    "setStatus",
    "stripPrefix"
})
public class Filter {

    @JsonProperty("addRequestHeader")
    private List<AddRequestHeader> addRequestHeader = null;
    @JsonProperty("addRequestParameter")
    private List<AddRequestParameter> addRequestParameter = null;
    @JsonProperty("addResponseHeader")
    private List<AddResponseHeader> addResponseHeader = null;
    @JsonProperty("dedupeResponseHeader")
    private List<DedupeResponseHeader> dedupeResponseHeader = null;
    @JsonProperty("prefixPath")
    private String prefixPath;
    @JsonProperty("preserveHostHeader")
    private Boolean preserveHostHeader;
    @JsonProperty("redirect")
    private Redirect redirect;
    @JsonProperty("removeRequestHeader")
    private List<String> removeRequestHeader = null;
    @JsonProperty("removeRequestParameter")
    private List<String> removeRequestParameter = null;
    @JsonProperty("removeResponseHeader")
    private List<String> removeResponseHeader = null;
    @JsonProperty("requestHeaderToRequestUri")
    private String requestHeaderToRequestUri;
    @JsonProperty("retry")
    private Integer retry;
    @JsonProperty("rewriteLocationResponseHeader")
    private List<RewriteLocationResponseHeader> rewriteLocationResponseHeader = null;
    @JsonProperty("rewritePath")
    private List<RewritePath> rewritePath = null;
    @JsonProperty("rewriteResponseHeader")
    private List<RewriteResponseHeader> rewriteResponseHeader = null;
    @JsonProperty("saveSession")
    private Boolean saveSession;
    @JsonProperty("secureHeaders")
    private Boolean secureHeaders;
    @JsonProperty("setPath")
    private String setPath;
    @JsonProperty("setRequestHeader")
    private List<SetRequestHeader> setRequestHeader = null;
    @JsonProperty("setRequestSize")
    private Long setRequestSize;
    @JsonProperty("setResponseHeader")
    private List<SetResponseHeader> setResponseHeader = null;
    @JsonProperty("setStatus")
    private Integer setStatus;
    @JsonProperty("stripPrefix")
    private Integer stripPrefix;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("addRequestHeader")
    public List<AddRequestHeader> getAddRequestHeader() {
        return addRequestHeader;
    }

    @JsonProperty("addRequestHeader")
    public void setAddRequestHeader(List<AddRequestHeader> addRequestHeader) {
        this.addRequestHeader = addRequestHeader;
    }

    @JsonProperty("addRequestParameter")
    public List<AddRequestParameter> getAddRequestParameter() {
        return addRequestParameter;
    }

    @JsonProperty("addRequestParameter")
    public void setAddRequestParameter(List<AddRequestParameter> addRequestParameter) {
        this.addRequestParameter = addRequestParameter;
    }

    @JsonProperty("addResponseHeader")
    public List<AddResponseHeader> getAddResponseHeader() {
        return addResponseHeader;
    }

    @JsonProperty("addResponseHeader")
    public void setAddResponseHeader(List<AddResponseHeader> addResponseHeader) {
        this.addResponseHeader = addResponseHeader;
    }

    @JsonProperty("dedupeResponseHeader")
    public List<DedupeResponseHeader> getDedupeResponseHeader() {
        return dedupeResponseHeader;
    }

    @JsonProperty("dedupeResponseHeader")
    public void setDedupeResponseHeader(List<DedupeResponseHeader> dedupeResponseHeader) {
        this.dedupeResponseHeader = dedupeResponseHeader;
    }

    @JsonProperty("prefixPath")
    public String getPrefixPath() {
        return prefixPath;
    }

    @JsonProperty("prefixPath")
    public void setPrefixPath(String prefixPath) {
        this.prefixPath = prefixPath;
    }

    @JsonProperty("preserveHostHeader")
    public Boolean getPreserveHostHeader() {
        return preserveHostHeader;
    }

    @JsonProperty("preserveHostHeader")
    public void setPreserveHostHeader(Boolean preserveHostHeader) {
        this.preserveHostHeader = preserveHostHeader;
    }

    @JsonProperty("redirect")
    public Redirect getRedirect() {
        return redirect;
    }

    @JsonProperty("redirect")
    public void setRedirect(Redirect redirect) {
        this.redirect = redirect;
    }

    @JsonProperty("removeRequestHeader")
    public List<String> getRemoveRequestHeader() {
        return removeRequestHeader;
    }

    @JsonProperty("removeRequestHeader")
    public void setRemoveRequestHeader(List<String> removeRequestHeader) {
        this.removeRequestHeader = removeRequestHeader;
    }

    @JsonProperty("removeRequestParameter")
    public List<String> getRemoveRequestParameter() {
        return removeRequestParameter;
    }

    @JsonProperty("removeRequestParameter")
    public void setRemoveRequestParameter(List<String> removeRequestParameter) {
        this.removeRequestParameter = removeRequestParameter;
    }

    @JsonProperty("removeResponseHeader")
    public List<String> getRemoveResponseHeader() {
        return removeResponseHeader;
    }

    @JsonProperty("removeResponseHeader")
    public void setRemoveResponseHeader(List<String> removeResponseHeader) {
        this.removeResponseHeader = removeResponseHeader;
    }

    @JsonProperty("requestHeaderToRequestUri")
    public String getRequestHeaderToRequestUri() {
        return requestHeaderToRequestUri;
    }

    @JsonProperty("requestHeaderToRequestUri")
    public void setRequestHeaderToRequestUri(String requestHeaderToRequestUri) {
        this.requestHeaderToRequestUri = requestHeaderToRequestUri;
    }

    @JsonProperty("retry")
    public Integer getRetry() {
        return retry;
    }

    @JsonProperty("retry")
    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    @JsonProperty("rewriteLocationResponseHeader")
    public List<RewriteLocationResponseHeader> getRewriteLocationResponseHeader() {
        return rewriteLocationResponseHeader;
    }

    @JsonProperty("rewriteLocationResponseHeader")
    public void setRewriteLocationResponseHeader(List<RewriteLocationResponseHeader> rewriteLocationResponseHeader) {
        this.rewriteLocationResponseHeader = rewriteLocationResponseHeader;
    }

    @JsonProperty("rewritePath")
    public List<RewritePath> getRewritePath() {
        return rewritePath;
    }

    @JsonProperty("rewritePath")
    public void setRewritePath(List<RewritePath> rewritePath) {
        this.rewritePath = rewritePath;
    }

    @JsonProperty("rewriteResponseHeader")
    public List<RewriteResponseHeader> getRewriteResponseHeader() {
        return rewriteResponseHeader;
    }

    @JsonProperty("rewriteResponseHeader")
    public void setRewriteResponseHeader(List<RewriteResponseHeader> rewriteResponseHeader) {
        this.rewriteResponseHeader = rewriteResponseHeader;
    }

    @JsonProperty("saveSession")
    public Boolean getSaveSession() {
        return saveSession;
    }

    @JsonProperty("saveSession")
    public void setSaveSession(Boolean saveSession) {
        this.saveSession = saveSession;
    }

    @JsonProperty("secureHeaders")
    public Boolean getSecureHeaders() {
        return secureHeaders;
    }

    @JsonProperty("secureHeaders")
    public void setSecureHeaders(Boolean secureHeaders) {
        this.secureHeaders = secureHeaders;
    }

    @JsonProperty("setPath")
    public String getSetPath() {
        return setPath;
    }

    @JsonProperty("setPath")
    public void setSetPath(String setPath) {
        this.setPath = setPath;
    }

    @JsonProperty("setRequestHeader")
    public List<SetRequestHeader> getSetRequestHeader() {
        return setRequestHeader;
    }

    @JsonProperty("setRequestHeader")
    public void setSetRequestHeader(List<SetRequestHeader> setRequestHeader) {
        this.setRequestHeader = setRequestHeader;
    }

    @JsonProperty("setRequestSize")
    public Long getSetRequestSize() {
        return setRequestSize;
    }

    @JsonProperty("setRequestSize")
    public void setSetRequestSize(Long setRequestSize) {
        this.setRequestSize = setRequestSize;
    }

    @JsonProperty("setResponseHeader")
    public List<SetResponseHeader> getSetResponseHeader() {
        return setResponseHeader;
    }

    @JsonProperty("setResponseHeader")
    public void setSetResponseHeader(List<SetResponseHeader> setResponseHeader) {
        this.setResponseHeader = setResponseHeader;
    }

    @JsonProperty("setStatus")
    public Integer getSetStatus() {
        return setStatus;
    }

    @JsonProperty("setStatus")
    public void setSetStatus(Integer setStatus) {
        this.setStatus = setStatus;
    }

    @JsonProperty("stripPrefix")
    public Integer getStripPrefix() {
        return stripPrefix;
    }

    @JsonProperty("stripPrefix")
    public void setStripPrefix(Integer stripPrefix) {
        this.stripPrefix = stripPrefix;
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
