package com.unknown.supportapp.client.ui.guiWindowsControllers.registrationWindow;


import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindowsControllers.confirmationCodeWindow.ConfirmationCodeWindowController;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.utils.account.AccountUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class RegistrationWindowController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void backButtonPressed(ActionEvent event) {
        emailField.clear();
        passwordField.clear();

        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
    }

    @FXML
    void nextButtonPressed(ActionEvent event) {
        try {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (!AccountUtils.checkAccountFormat(email, password)) {
                Stage stage = WindowFactory.getFactory().getStage(WindowConfig.WrongAccountFormatWindow);
                stage.setResizable(false);
                stage.showAndWait();
                return;
            }

            boolean check = ClientServicesFactory.getFactory().getCheckAccountExistenceService().check(email);
            if (check) {
                WindowFactory.getFactory().showStage(WindowConfig.AccountAllreadyExistWindow);
                return;
            }

            String confirmationCode = ClientServicesFactory.getFactory().getRegistrationService().registration(email, password);
            System.out.println(confirmationCode);

            emailField.clear();
            passwordField.clear();

            ConfirmationCodeWindowController controller = WindowFactory.getFactory().getController(WindowConfig.ConfirmationCodeWindow);
            controller.setEmail(email);
            controller.setPassword(password);
            controller.setRegistration(true);
            controller.setConfirmationCode(confirmationCode);

            WindowFactory.getFactory().showStage(WindowConfig.ConfirmationCodeWindow);
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    @FXML
    void passwordFieldEnter(ActionEvent event) {
        nextButtonPressed(event);
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

}
