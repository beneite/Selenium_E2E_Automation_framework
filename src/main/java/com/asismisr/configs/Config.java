package com.asismisr.configs;

import com.asismisr.exceptions.ResourceNotFoundExceptions;
import com.asismisr.utils.resourceloader.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class Config {

    private Config(){

    }

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String CONFIG_FILE_LOCATION = "config/default.properties";
    private static Properties properties;

    /**
     * This method will override the default properties which are provided in config.properties(CONFIG_FILE_LOCATION)
     * Suppose if a use runs a test via a command line with different browser, other property..., then those properties should be loaded
     */
    public static void initializeProperties(){

        // load the default properties
        properties = loadProperties();
        // overriding the default properties
        for(String key : properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key).trim());
            }
        }

        // printing the properties
        log.info("Below the properties for test:");
        for(String key : properties.stringPropertyNames()){
            log.info("{}:{}",key,properties.getProperty(key));
        }
        log.info("*********************** Config initialization completed Successfully ***********************");
    }

    /**
     * created this method in case any test method wants to access any key/property
     * @param key key/property
     * @return property value
     */
    public static String getTestProperty(String key){
        String value = properties.getProperty(key);
        if(Objects.isNull(value))
            throw new ResourceNotFoundExceptions("Value for {} not found in default.properties file for"+ key);
        return value;
    }

    public static Properties loadProperties(){

        Properties properties = new Properties();

        try(InputStream inputStream = ResourceLoader.getResource(CONFIG_FILE_LOCATION)){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new ResourceNotFoundExceptions("Failed to load the config file:"+ CONFIG_FILE_LOCATION+ e);
        }
        return properties;
    }

}
