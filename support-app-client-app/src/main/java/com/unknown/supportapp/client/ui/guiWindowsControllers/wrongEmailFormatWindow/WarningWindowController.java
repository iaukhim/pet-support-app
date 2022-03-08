package com.unknown.supportapp.client.ui.guiWindowsControllers.wrongEmailFormatWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WarningWindowController {

    @FXML
    void okButtonPressed(ActionEvent event) {
        WindowFactory.getFactory().hideStage(WindowConfig.WrongEmailFormatWindow);

    }

}
