package com.http.server.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The `Config` class represents the configuration settings for the HTTP server.
 * It includes properties for the server port and the web root directory.
 * @autor Yassin Hamdi
 */

public class Config {

    /**
     * listen for incoming connections on this port
     */
    @JsonProperty("port")
    private int port ;
    /**
     * The root directory for web resources (basicly web server where files are coming from)
     */
    @JsonProperty("webroot")
    private String webRoot ;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }
}
