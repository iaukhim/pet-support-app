package com.unknown.supportapp.client.ui.guiWindowsControllers.confirmationCodeWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindowsControllers.newPasswordWindow.NewPasswordWindowController;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ConfirmationCodeWindowController {

    private String confirmationCode;

    private String email;

    private String password;

    private boolean registration;

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }


    @FXML
    private TextField textField;

    @FXML
    private Label wrongCode;

    @FXML
    void okButtonPressed(ActionEvent event) {

        if (registration) {
            if (confirmationCode.equals(textField.getText())) {
                wrongCode.setVisible(false);
                ClientServicesFactory.getFactory().getConfirmationService().confirmation(email, password);
                textField.clear();
                WindowFactory.getFactory().hideStage(WindowConfig.ConfirmationCodeWindow);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Registration finished. Now you can log in system whit your account");
                alert.show();

                WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
                return;
            }
            wrongCode.setVisible(true);
        }
        if (confirmationCode.equals(textField.getText())) {
            wrongCode.setVisible(false);

            WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.NewPasswordWindow);

            NewPasswordWindowController controller = WindowFactory.getFactory().getController(WindowConfig.NewPasswordWindow);
            controller.setEmail(email);

            WindowFactory.getFactory().hideStage(WindowConfig.ConfirmationCodeWindow);
            textField.clear();
            return;
        }
        wrongCode.setVisible(true);
    }
}
