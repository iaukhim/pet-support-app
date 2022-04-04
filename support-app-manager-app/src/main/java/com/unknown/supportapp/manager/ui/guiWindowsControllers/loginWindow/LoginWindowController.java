package com.unknown.supportapp.manager.ui.guiWindowsControllers.loginWindow;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.service.manager.ManagerLoginService;
import com.unknown.supportapp.common.dto.manager.ManagerDto;
import com.unknown.supportapp.manager.ui.factory.WindowFactory;
import com.unknown.supportapp.manager.ui.factory.WindowConfig;
import com.unknown.supportapp.manager.ui.guiWindowsControllers.startWindow.StartWindowController;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginWindowController {

    @FXML
    private AnchorPane LoginWindowController;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void loginButtonPressed(ActionEvent event) {
        try {
            String email = emailField.getText();
            String password = passwordField.getText();

            ManagerDto managerDto = new ManagerDto();
            managerDto.setEmail(email);
            managerDto.setPassword(password);
            ManagerLoginService logInService = ClientServicesFactory.getFactory().getManagerLoginService();

            if(logInService.login(managerDto)){
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

}
