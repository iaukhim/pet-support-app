package com.unknown.supportapp.client.ui.factory;

import javafx.scene.Scene;

public class SceneContainer {

    private Scene scene;

    private Object controller;

    public Scene getScene() {
        return scene;
    }

    public SceneContainer(Scene scene, Object controller) {
        this.scene = scene;
        this.controller = controller;
    }

    @SuppressWarnings("unchecked")
    public <C> C getController() {
        return (C) controller;
    }
}
