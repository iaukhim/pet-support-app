package com.unknown.server.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServerSettings {

    private static final String SERVER_PROPERTIES_FILE = "./support-app/server/server-settings.properties";

    private static final ServerSettings settings = new ServerSettings();

    private  Properties properties;

    private ServerSettings(){
        properties = new Properties();
        try {
            properties.load(new FileReader(SERVER_PROPERTIES_FILE));
        }
        catch (IOException e){
            throw new RuntimeException("Error occurred, trying to load server properties", e);
        }

    }

    public static ServerSettings getSettings(){
        return settings;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getPathToDb(){
        return properties.getProperty("accounts-db-filepath");
    }

    public int getServerPort(){
        return Integer.parseInt(properties.getProperty("server-port"));
    }
}
