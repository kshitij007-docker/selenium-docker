package org.kshitijselenium.util;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;
    public static void initialize() {

        //step 1 : load default properties
        properties=loadProperties();

        //Step 2:  check for any override --if we can override the values present in config using command line
        for(String key: properties.stringPropertyNames()) //It will get keys present in config/default.properties
        {

            if(System.getProperties().containsKey(key)) //it will check if any value provided from command line for the key present in config
            {
                properties.setProperty(key,System.getProperty(key));
            }

        }

        //Step 3: print -- print the properties
        log.info("Test properties");
        log.info("___________________________");
        for(String key:properties.stringPropertyNames())
        {

            log.info("{}={}",key,properties.getProperty(key)); //because of curly braces {} ={} both key and value are printed
        }
        log.info("__________________________");
    }

    public static String get(String key) //this method can be used to get property via config
    {
        return properties.getProperty(key);
    }

    public static Properties loadProperties()
    {
        Properties properties=new Properties();
        try(InputStream stream=ResourceLoader.getResource(DEFAULT_PROPERTIES)) {
            properties.load(stream);
        } catch (Exception e) {
            log.error("Unable to read the properties {}",DEFAULT_PROPERTIES);
        }
        return properties;
    }

}
