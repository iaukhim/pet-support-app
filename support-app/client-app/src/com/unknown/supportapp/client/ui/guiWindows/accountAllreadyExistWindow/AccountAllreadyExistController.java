package com.unknown.supportapp.client.ui.guiWindows.accountAllreadyExistWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AccountAllreadyExistController {

    @FXML
    void okButtonPressed(ActionEvent event) {
        WindowFactory.getFactory().hideStage(WindowConfig.AccountAllreadyExistWindow);
    }

}
