package com.unknown.supportapp.server.spring.config;

import com.unknown.supportapp.server.common.ResponseFactory;
import com.unknown.supportapp.server.common.ServerSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.ServerSocket;

@Configuration
@ComponentScan("com.unknown.supportapp.server")
public class ServerConfig {

    @Bean
    public ResponseFactory responseFactory(){
        return ResponseFactory.getFactory();
    }

    @Bean
    public ServerSettings serverSettings(){
        return ServerSettings.getSettings();
    }
}
