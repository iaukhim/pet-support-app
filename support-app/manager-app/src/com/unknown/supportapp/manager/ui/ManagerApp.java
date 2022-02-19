package com.unknown.supportapp.manager.ui;

import com.unknown.supportapp.manager.ui.factory.WindowConfig;
import com.unknown.supportapp.manager.ui.factory.WindowFactory;
import javafx.application.Application;

import javafx.stage.Stage;

public class ManagerApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        WindowFactory.getFactory().addPrimaryWindow(stage);
        stage.setResizable(false);
        WindowFactory.getFactory().showStage(WindowConfig.PrimaryWindow);
    }
}
