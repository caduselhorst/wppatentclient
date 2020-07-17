/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author pge
 */
public class Config {
    
    private static Config config;
    private Properties properties;
    
    protected Config() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(new File("config.properties")));
    }
    
    public static Config getInstance() throws IOException {
        if(config == null) {
            config = new Config();
        }
        
        return config;
    }
    
    public String appUrlApi() {
        return properties.getProperty("app.url.api");
    }
    
    public long protocoloTimePooling() {
        return Long.parseLong(properties.getProperty("app.protocolo.pooling.time"));
    }
    
    
    public long chatTimePooling() {
        return Long.parseLong(properties.getProperty("app.chat.pooling.time"));
    }
    
    public String getFileExtensionByMimeType(String mimeType) {
        return StringUtils.split(properties.getProperty(mimeType),",")[0];
    }
    
    public Set<Map.Entry<Object, Object>> getAllProperties() {
        return properties.entrySet();
    }
}
