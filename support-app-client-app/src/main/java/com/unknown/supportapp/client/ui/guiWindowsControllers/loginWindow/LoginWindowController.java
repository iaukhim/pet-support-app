package com.unknown.supportapp.client.ui.guiWindowsControllers.loginWindow;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindowsControllers.startWindow.StartWindowController;
import com.unknown.supportapp.client.common.service.account.LogInService;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.utils.account.AccountUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        try {
            String email = emailField.getText();
            String password = passwordField.getText();

            if(!AccountUtils.checkEmailFormat(email)){
                Stage stage = WindowFactory.getFactory().getStage(WindowConfig.WrongEmailFormatWindow);
                stage.setResizable(false);
                stage.showAndWait();
                return;
            }

            LogInService logInService = ClientServicesFactory.getFactory().getLogInService();
            boolean result = logInService.LogIn(email, password);
            if(result){
                Scene scene = WindowFactory.getFactory().getScene(WindowConfig.StartWindow);

                StartWindowController controller = WindowFactory.getFactory().getController(WindowConfig.StartWindow);
                controller.setEmail(email);

                WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, scene);
                passwordField.clear();
                emailField.clear();
            }
            else {
                WindowFactory.getFactory().showStage(WindowConfig.WrongEmailOrPasswordWindow);
            }
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
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
