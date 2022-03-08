package com.unknown.supportapp.manager.ui.guiWindowsControllers.wrongEmailOrPasswordWindow;

import com.unknown.supportapp.manager.ui.factory.WindowFactory;
import com.unknown.supportapp.manager.ui.factory.WindowConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WrongEmailOrPasswordController {

    @FXML
    void okButtonPressed(ActionEvent event) {
        WindowFactory.getFactory().hideStage(WindowConfig.WrongEmailOrPasswordWindow);

    }

}




