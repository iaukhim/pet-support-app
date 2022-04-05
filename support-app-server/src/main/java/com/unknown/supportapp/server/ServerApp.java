package com.unknown.supportapp.server;

import com.unknown.supportapp.server.spring.AppContext;


public class ServerApp {

    public static void main(String[] args) {
        Server bean = AppContext.getAppContext().getContext().getBean(Server.class);
        bean.runServer();
    }
}

