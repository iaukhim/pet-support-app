package com.unknown.supportapp.client.ui.guiWindows.startWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindows.productsWindow.ProductsWindowController;
import com.unknown.supportapp.client.ui.guiWindows.profileWindow.ProfileWindowController;
import com.unknown.supportapp.client.ui.guiWindows.ticketsWindow.TicketsWindowController;
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
    void productsButtonPressed(ActionEvent event) {
        ProductsWindowController controller = WindowFactory.getFactory().getController(WindowConfig.ProductsWindow);
        controller.setEmail(email);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.ProductsWindow);
    }

    @FXML
    void profileButtonPressed(ActionEvent event) {
        ProfileWindowController controller = WindowFactory.getFactory().getController(WindowConfig.ProfileWindow);
        controller.setEmail(email);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.ProfileWindow);
    }

    @FXML
    void ticketsButtonPressed(ActionEvent event) {
        TicketsWindowController controller  = WindowFactory.getFactory().getController(WindowConfig.TickectsWindow);
        controller.setEmail(email);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.TickectsWindow);
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
    private void logOut(){
        email = null;
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
    }

}

