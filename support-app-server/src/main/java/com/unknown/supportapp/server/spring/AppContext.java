package com.unknown.supportapp.server.spring;

import com.unknown.supportapp.server.spring.config.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {

    private static final AppContext appContext = new AppContext();

    private final ApplicationContext context;

    public AppContext() {
        context = new AnnotationConfigApplicationContext(ServerConfig.class, ControllersConfig.class, ServicesConfig.class, DaoConfig.class, JpaConfig.class);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public static AppContext getAppContext(){
        return appContext;
    }
}
