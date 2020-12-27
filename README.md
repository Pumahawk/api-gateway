# API Gateway

Embanded API gateway focuses on customized configurations and extensions.

It's possible to divide logic in 3 importat points of abstract configuration location.

* **Loaders**  
These classes are responsable of retrieving configuration location and converting it in a *InputStream*.

* **Readers**  
These classes are responsable of interpreting *InputStream* given by *Loaders* and converting it in a *TreeNode*. *TreeNode* is an object of the **Jackson-core** library.

* **Solvers**  
These classes are responsable of interpreting *TreeNode* and consuming a *PredicateSpec* to create the final API gateway configuration.

## Configuration file

To create a configuration for API gateway you must create `gateway.json` file with the basic configuration.

To use a different location file it is possibile to define it with a **fileConfigurationPath** system property.

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

API Gateway has different classes that are already implemented.

### Loaders

---

**Class name**:  `com.github.pumahawk.apigateway.processors.FileLoaderConfiguration`

**Description**

Loaders can read the configurations from a directory or a file in the filesystem.

**Properties**:

* **location**: Directory or file with the routes' configurations inside.

**Return**: For each file, an InputStream with a **type**, which is valorized with the extension of the file, returns.

---

### Readers

---

**Class name**: `com.github.pumahawk.apigateway.processors.JsonReaderConfiguration`

**Description**

Read a  `json` input stream and convert it directly in a *TreeNode*.

**Properties**: none

**Return**: *TreeNode* of the entire json given by the input stream.

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

It's possible to add custom class inside a jar putting it in a special directory dedicated to the extension using a **loader.path** system property.