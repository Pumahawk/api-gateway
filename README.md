# API Gateway

Embanded API gateway focus on customize configurations and extensions.

It' possible divides logic in 3 importat points of abstract configuration location.

* **Loaders**  
Classes responsable to retrive configuration location and convert it in a *InputStream*.

* **Readers**  
Classes responsable to interpret *InputStream* given by *Loaders* and convert it in a *TreeNode*. *TreeNode* is an object from library **Jackson-core**.

* **Solvers**  
Classes responsable to interpret *TreeNode* and consume a *PredicateSpec* to create the final API gateway configuration.

## Configuration file

To create a configuration for API gateway you must create `gateway.json` file with the basic configuration.

To use a different location file is possibile define it with a system property **fileConfigurationPath**.

```json
{
    "loaders": [
        {
            "className": "com.github.pumahawk.apigateway.processors.FileLoaderConfiguration",
            "properties": {
                "location": "routes"
            }
        }
    ],
    "readers": [
        {
            "className": "com.github.pumahawk.apigateway.processors.JsonReaderConfiguration"
        }
    ],
    "solvers": [
        {
            "className": "com.github.pumahawk.apigateway.processors.SimpleSolverConfiguration"
        }
    ]
}

```

## Configuration classes

API Gateway have differents classes already implemented.

### Loaders

---

**Class name**:  `com.github.pumahawk.apigateway.processors.FileLoaderConfiguration`

**Description**

Can read configuration from directory or file in the filesystem.

**Properties**:

* **location**: Directory or file with routers configuration inside.

**Return**: Return a InputStream of each file with **type** volorized with the extension of the file.

---

### Readers

---

**Class name**: `com.github.pumahawk.apigateway.processors.JsonReaderConfiguration`

**Description**

Read input stream of type `json` and convert directly in a *TreeNode*.

**Properties**: none

**Return**: *TreeNode* of entire json given by input stream.

---

### Solvers

---

**Class name**: `com.github.pumahawk.apigateway.processors.SimpleSolverConfiguration`

**Type**: simple

**Description**

Simple wrapper of class PredicateSpec to create a route directly with Spring library.

**Properties**: none

**Route configuration in json format**:

```json
{
    "type": "simple",
    "alwaysTrue": true,
    "method": "string",
    "host": [
        "string"
    ],
    "path": [
        "string"
    ],
    "remoteAddr": [
        "string"
    ],
    "filter": {
        "addRequestHeader": [
            {
                "headerName": "string",
                "headerValue": "string"
            }
        ],
        "addRequestParameter": [
            {
                "param": "string",
                "value": "string"
            }
        ],
        "addResponseHeader": [
            {
                "headerName": "string",
                "headerValue": "string"
            }
        ],
        "dedupeResponseHeader": [
            {
                "headerName": "string",
                "strategy": "string"
            }
        ],
        "prefixPath": "string",
        "preserveHostHeader": false,
        "redirect": {
            "status": 200,
            "url": "string"
        },
        "removeRequestHeader": [
            "string"
        ],
        "removeRequestParameter": [
            "string"
        ],
        "removeResponseHeader": [
            "string"
        ],
        "requestHeaderToRequestUri": "string",
        "retry": 5,
        "rewriteLocationResponseHeader": [
            {
                "stripVersionMode": "string",
                "locationHeaderName": "string",
                "hostValue": "string",
                "protocolsRegex": "string"
            }
        ],
        "rewritePath": [
            {
                "regex": "string",
                "replacement": "string"
            }
        ],
        "rewriteResponseHeader": [
            {
                "headerName": "string",
                "regex": "string",
                "replacement": "string"
            }
        ],
        "saveSession": false,
        "secureHeaders": true,
        "setPath": "string",
        "setRequestHeader": [
            {
                "headerName": "String",
                "headerValue": "String"
            }
        ],
        "setRequestSize": -1,
        "setResponseHeader": [
            {
                "headerName": "string",
                "headerValue": "string"
            }
        ],
        "setStatus": 200,
        "stripPrefix": -1
    },
    "uri": "https://httpbin.org"
}
```

---

## Extension

It's possible to add custom class inside a jar putting in a special directory dedicated of extension using a system property **loader.path**.