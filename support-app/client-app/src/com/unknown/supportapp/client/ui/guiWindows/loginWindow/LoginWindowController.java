package com.unknown.supportapp.client.ui.guiWindows.loginWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindows.startWindow.StartWindowController;
import com.unknown.supportapp.client.common.service.account.LogInService;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.utils.account.AccountUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginWindowController {

    @FXML
    private AnchorPane LoginWindowController;

    @FXML
    private TextField emailField;

    @FXML
    private Button forgotButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void forgotButtonPressed(ActionEvent event) {
        passwordField.clear();
        emailField.clear();

        Scene scene = WindowFactory.getFactory().getScene(WindowConfig.ForgetPasswordWindow);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, scene);
    }

    @FXML
    void loginButtonPressed(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if(!AccountUtils.checkEmailFormat(email)){
            Stage stage = WindowFactory.getFactory().getStage(WindowConfig.WrongEmailFormatWindow);
            stage.setResizable(false);
            stage.showAndWait();
            return;
        }

        LogInService logInService = ClientServicesFactory.getFactory().getLogInService();
        if(logInService.LogIn(email, password)){
            Scene scene = WindowFactory.getFactory().getScene(WindowConfig.StartWindow);

            StartWindowController controller = WindowFactory.getFactory().getController(WindowConfig.StartWindow);
            controller.setEmail(email);

            WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, scene);
            passwordField.clear();
            emailField.clear();
        }
        else {
            WindowFactory.getFactory().showStage(WindowConfig.WrongEmailOrPasswordWindow);
        };
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
    void passwordFieldEnter(ActionEvent event) {
        loginButtonPressed(event);
    }

    @FXML
    void registerButtonPressed(ActionEvent event) {
        passwordField.clear();
        emailField.clear();

        Scene scene = WindowFactory.getFactory().getScene(WindowConfig.RegistrationWindow);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, scene);

    }

}
