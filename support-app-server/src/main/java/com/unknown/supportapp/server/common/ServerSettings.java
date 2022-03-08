package com.unknown.supportapp.server.common;

import java.io.IOException;
import java.util.Properties;

public class ServerSettings {

    private static final ServerSettings settings = new ServerSettings();

    private  Properties properties;

    private ServerSettings(){
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("server-settings.properties"));
        }
        catch (IOException e){
            throw new RuntimeException("Error occurred, while trying to load server properties", e);
        }
    }

    public static ServerSettings getSettings(){
        return settings;
    }

    public int getServerPort(){
        return Integer.parseInt(properties.getProperty("server-port"));
    }
}
