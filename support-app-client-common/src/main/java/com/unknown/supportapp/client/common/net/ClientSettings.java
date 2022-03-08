package com.unknown.supportapp.client.common.net;

import java.io.IOException;
import java.util.Properties;

public class ClientSettings {

    private static final ClientSettings settings = new ClientSettings();

    private Properties properties;

    private ClientSettings(){
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("client-settings.properties"));
        }
        catch (IOException e){
            throw new RuntimeException("Error occurred, trying to load client properties", e);
        }

    }

    public static ClientSettings getSettings(){
        return settings;
    }

    public String getServerIp(){
        return properties.getProperty("server-ip");
    }

    public int getServerPort(){
        return Integer.parseInt(properties.getProperty("server-port"));
    }
}
