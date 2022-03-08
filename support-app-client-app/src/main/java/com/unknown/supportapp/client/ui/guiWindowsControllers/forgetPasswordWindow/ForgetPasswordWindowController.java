package com.unknown.supportapp.client.ui.guiWindowsControllers.forgetPasswordWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindowsControllers.confirmationCodeWindow.ConfirmationCodeWindowController;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.utils.account.AccountUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ForgetPasswordWindowController {


    @FXML
    private AnchorPane LoginWindowPane;

    @FXML
    private Button backButton;

    @FXML
    private TextField emailField;

    @FXML
    private Button nextButton;

    @FXML
    void backButtonPressed(ActionEvent event) {
        emailField.clear();

        Scene scene = WindowFactory.getFactory().getScene(WindowConfig.LoginWindow);
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
        String email = emailField.getText();

        if(!AccountUtils.checkEmailFormat(email)){
            Stage stage = WindowFactory.getFactory().getStage(WindowConfig.WrongEmailFormatWindow);
            stage.setResizable(false);
            stage.showAndWait();
            return;
        }
        boolean check = ClientServicesFactory.getFactory().getCheckAccountExistenceService().check(email);

        if (!check) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fail");
            alert.setContentText("There is no account with such email");
            alert.showAndWait();
            return;
        }

        String confirmationCode = ClientServicesFactory.getFactory().getConfirmationCodeService().send(email);
        System.out.println(confirmationCode);

        ConfirmationCodeWindowController controller = WindowFactory.getFactory().getController(WindowConfig.ConfirmationCodeWindow);

        controller.setConfirmationCode(confirmationCode);
        controller.setRegistration(false);
        controller.setEmail(email);

        emailField.clear();

        WindowFactory.getFactory().getStage(WindowConfig.ConfirmationCodeWindow).show();

    }

}

