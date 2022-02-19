package com.unknown.supportapp.manager.ui.guiWindows.wrongEmailOrPasswordWindow;

import com.unknown.supportapp.manager.ui.factory.WindowConfig;
import com.unknown.supportapp.manager.ui.factory.WindowFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WrongEmailOrPasswordController {

    @FXML
    void okButtonPressed(ActionEvent event) {
        WindowFactory.getFactory().hideStage(WindowConfig.WrongEmailOrPasswordWindow);

    }

}




