package com.unknown.supportapp.manager.ui.factory;

import com.unknown.supportapp.manager.ui.exceptions.UiLoadingException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WindowFactory {

    private static final WindowFactory factory = new WindowFactory();

    public static WindowFactory getFactory() {
        return factory;
    }

    private Map<WindowConfig, SceneContainer> windows = new HashMap<>();

    private Map<WindowConfig, Stage> stageMap = new HashMap<>();

    private WindowFactory() {
    }

    public void addPrimaryWindow(Stage primaryStage) {
        SceneContainer window = createWindow(WindowConfig.PrimaryWindow, primaryStage);
        windows.put(WindowConfig.PrimaryWindow, window);
    }

    public void setScene(WindowConfig config, Scene scene){
        getStage(config).setScene(scene);
    }

    public void setScene(WindowConfig config, WindowConfig configSource){
        getStage(config).setScene(getScene(configSource));
    }

    public Scene getScene(WindowConfig config){
        SceneContainer window = getWindow(config);
        return window.getScene();
    }

    public SceneContainer getWindow(WindowConfig config) {
        SceneContainer window = windows.get(config);
        if (window == null) {
            window = createWindow(config);
            windows.put(config, window);
        }

        return window;
    }

    public Stage getStage(WindowConfig config) {
        Stage stage = stageMap.get(config);
        if (stage == null) {
            stage = new Stage();
            stage.setTitle(config.getTitle());
            stage.setScene(getScene(config));
            stageMap.put(config, stage);
        }
        return stage;
    }

    public <C> C getController(WindowConfig config) {
        return getWindow(config).getController();
    }

    public void showStage(WindowConfig config) {
        getStage(config).show();
    }

    public void hideStage(WindowConfig config) {
        getStage(config).hide();
        getStage(config).setScene(getScene(config));
    }

    private SceneContainer createWindow(WindowConfig config) {
        return createWindow(config, new Stage());
    }

    private SceneContainer createWindow(WindowConfig config, Stage stage) {
        URL fxmlUrl = fxmlUrl = getClass().getResource(config.getFxmlPath());
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

        Parent ui = null;
        try {
            ui = fxmlLoader.load();
        } catch (IOException e) {
            throw new UiLoadingException(config, e);
        }

        Scene scene = new Scene(ui, config.getWidth(), config.getHeight());
        stage.setScene(scene);
        stage.setTitle(config.getTitle());
        if (config.isModality()){
            stage.initModality(Modality.APPLICATION_MODAL);
        }

        Object controller = fxmlLoader.getController();
        SceneContainer window = new SceneContainer(scene, controller);
        stageMap.put(config, stage);

        return  window;
    }

}
