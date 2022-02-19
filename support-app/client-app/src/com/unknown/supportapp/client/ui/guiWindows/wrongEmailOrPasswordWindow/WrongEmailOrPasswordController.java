package com.unknown.supportapp.client.ui.guiWindows.wrongEmailOrPasswordWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WrongEmailOrPasswordController {

    @FXML
    void okButtonPressed(ActionEvent event) {
        WindowFactory.getFactory().hideStage(WindowConfig.WrongEmailOrPasswordWindow);

    }

}




