package com.unknown.supportapp.client.common.net;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ClientSettings {

    private static final String CLIENT_PROPERTIES_FILE = "./support-app/client-common/client-settings.properties";

    private static final ClientSettings settings = new ClientSettings();

    private Properties properties;

    private ClientSettings(){
        properties = new Properties();
        try {
            properties.load(new FileReader(CLIENT_PROPERTIES_FILE));
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
