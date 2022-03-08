package com.unknown.supportapp.client.ui.guiWindowsControllers.wrongAccountFormatWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WrongAccountFormatController {

    @FXML
    void okButtonPressed(ActionEvent event) {
        WindowFactory.getFactory().hideStage(WindowConfig.WrongAccountFormatWindow);
    }

}
