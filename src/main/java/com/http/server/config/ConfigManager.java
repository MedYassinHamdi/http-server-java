package com.http.server.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.http.server.exceptions.HttpConfigException;
import com.http.server.util.Json;

import java.io.FileReader;
import java.io.IOException;

/**
 * Singleton class to manage server configuration
 * @author Yassin Hamdi
 */

public class ConfigManager {

    private static ConfigManager myConfigManager ;
    private static Config myCurrentConfig ;

    private ConfigManager(){

    }

    /**
     * Get the singleton instance of ConfigManager
     * @return ConfigManager instance
     */
    public static ConfigManager getInstance(){
        if(myConfigManager == null){
            myConfigManager = new ConfigManager() ;
        }
        return myConfigManager ;
    }

    /**
     * Load configuration from file by path provider
     * @param filePath Path to the configuration file
     * @return Config object
     */
    public void loadConfigFile(String filePath) throws HttpConfigException {

        StringBuffer stringBuffer = new StringBuffer();

        // Use try-with-resources to ensure the FileReader is closed and
        // use -1 as the EOF sentinel (read() returns -1 at end-of-file).
        try (FileReader fileReader = new FileReader(filePath)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(stringBuffer.toString());
        } catch (IOException e) {
            throw new HttpConfigException("Error parsing the Config file.", e) ;
        }

        // Map values manually to be resilient to types (e.g. "port": "8095")
        try {
            int port;
            JsonNode portNode = conf.path("port");
            if (portNode.isInt()) {
                port = portNode.asInt();
            } else if (portNode.isTextual()) {
                try {
                    port = Integer.parseInt(portNode.asText());
                } catch (NumberFormatException nfe) {
                    throw new HttpConfigException("Invalid port value in config: not a number", nfe);
                }
            } else {
                throw new HttpConfigException("Missing or invalid 'port' value in config.");
            }

            String webroot = conf.path("webroot").asText(null);
            if (webroot == null || webroot.isEmpty()) {
                throw new HttpConfigException("Missing or empty 'webroot' value in config.");
            }

            Config cfg = new Config();
            cfg.setPort(port);
            cfg.setWebRoot(webroot);
            myCurrentConfig = cfg;

        } catch (HttpConfigException e) {
            throw e;
        } catch (Exception e) {
            throw new HttpConfigException("Error mapping the config file.", e);
        }


    }

    /**
     *Returns the curent loaded configs
     */
    public Config getCurrentConfig() throws HttpConfigException {

        if (myCurrentConfig==null){
            throw new HttpConfigException("no current Config set.");
        }

        return myCurrentConfig ;

    }

}
