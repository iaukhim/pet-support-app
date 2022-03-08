package com.unknown.supportapp.client.ui;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import javafx.application.Application;

import javafx.stage.Stage;

public class ClientApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        WindowFactory.getFactory().addPrimaryWindow(stage);
        stage.setResizable(false);
        WindowFactory.getFactory().showStage(WindowConfig.PrimaryWindow);
    }

    public static void main(String[] args) {
        launch();
    }
}
