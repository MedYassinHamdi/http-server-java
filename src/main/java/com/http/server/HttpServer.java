package com.http.server;

import com.http.server.config.Config;
import com.http.server.config.ConfigManager;
import com.http.server.exceptions.HttpConfigException;

/**
 * Driver Class for HTTP Server
 */


public class HttpServer {
    public static void main(String[] args) throws HttpConfigException {
        System.out.println("HTTP Server is running...");

        ConfigManager.getInstance().loadConfigFile("src/main/resources/http.json");
        Config config = ConfigManager.getInstance().getCurrentConfig();
        System.out.println("Server is listening on port: " + config.getPort());
        System.out.println("Web root directory: " + config.getWebRoot());
    }
}
