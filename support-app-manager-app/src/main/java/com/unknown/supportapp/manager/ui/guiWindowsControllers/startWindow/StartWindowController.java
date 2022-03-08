package com.unknown.supportapp.manager.ui.guiWindowsControllers.startWindow;

import com.unknown.supportapp.manager.ui.factory.WindowFactory;
import com.unknown.supportapp.manager.ui.factory.WindowConfig;
import com.unknown.supportapp.manager.ui.guiWindowsControllers.ticketsWindow.TicketsWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartWindowController {

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void ticketsButtonPressed(ActionEvent event) {
        TicketsWindowController controller  = WindowFactory.getFactory().getController(WindowConfig.TicketsWindow);
        controller.setEmail(email);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.TicketsWindow);
    }

    @FXML
    void mouseEnteredButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-border-color: #ffa700; -fx-background-color: #171717;");
    }

    @FXML
    void mouseExitedButton(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle("-fx-border-color: transparent; -fx-background-color: #171717; -fx-border-color: #272829");
    }

    @FXML
    void logOut(ActionEvent event) {
        email = null;
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
    }

}

