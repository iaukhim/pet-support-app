package com.unknown.supportapp.server.spring;

import com.unknown.supportapp.server.spring.config.ControllersConfig;
import com.unknown.supportapp.server.spring.config.DaoConfig;
import com.unknown.supportapp.server.spring.config.JpaConfig;
import com.unknown.supportapp.server.spring.config.ServicesConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {

    private static final AppContext appContext = new AppContext();

    private final ApplicationContext context;

    public AppContext() {
        context = new AnnotationConfigApplicationContext(ControllersConfig.class, ServicesConfig.class, DaoConfig.class, JpaConfig.class);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public static AppContext getAppContext(){
        return appContext;
    }
}
