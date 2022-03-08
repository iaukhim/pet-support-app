package com.unknown.supportapp.client.ui.guiWindowsControllers.newPasswordWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.utils.account.AccountUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class NewPasswordWindowController {

    private String email;

    @FXML
    private AnchorPane LoginWindowPane;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private PasswordField passwordField;


    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void backButtonPressed(ActionEvent event) {
        passwordField.clear();

        Scene scene = WindowFactory.getFactory().getScene(WindowConfig.ForgetPasswordWindow);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, scene);
    }

    @FXML
    void mouseEntered(MouseEvent event) {
        Button source = (Button) event.getSource();
        source.setStyle("-fx-border-color: #ffb153; ");
    }

    @FXML
    void mouseExited(MouseEvent event) {
        Button source = (Button) event.getSource();
        source.setStyle("-fx-border-color: transparent; ");
    }

    @FXML
    void nextButtonPressed(ActionEvent event) {
        String password = passwordField.getText();

        if (AccountUtils.checkPasswordFormat(password)){
            ClientServicesFactory.getFactory().getChangePasswordService().change(email, password);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Password changed. Now you can log in system with your account");
            alert.show();

            Scene scene = WindowFactory.getFactory().getScene(WindowConfig.LoginWindow);
            WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, scene);

            passwordField.clear();
            return;
        }
        else {
            WindowFactory.getFactory().showStage(WindowConfig.WrongEmailOrPasswordWindow);
        }
    }
}
