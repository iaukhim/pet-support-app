package com.unknown.supportapp.server.spring;

import com.unknown.supportapp.server.spring.config.ControllersConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {

    private static final AppContext appContext = new AppContext();

    private final ApplicationContext context;

    public AppContext() {
        context = new AnnotationConfigApplicationContext(ControllersConfig.class);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public static AppContext getAppContext(){
        return appContext;
    }
}
