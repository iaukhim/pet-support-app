package com.unknown.supportapp.client.ui.guiWindowsControllers.profileWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindowsControllers.productsWindow.ProductsWindowController;
import com.unknown.supportapp.client.ui.guiWindowsControllers.ticketsWindow.TicketsWindowController;
import com.unknown.supportapp.common.dto.acccount.AccountDto;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.utils.account.AccountUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ProfileWindowController {

    private String email;

    private AccountDto accountDto;

    public void setEmail(String email) {
        this.email = email;
        initialize();
    }

    @FXML
    private DatePicker birthdayField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField surnameField;

    @FXML
    void productsButtonPressed(ActionEvent event) {
        ProductsWindowController controller = WindowFactory.getFactory().getController(WindowConfig.ProductsWindow);
        controller.setEmail(email);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.ProductsWindow);
        clearFields();
    }

    @FXML
    void ticketsButtonPressed(ActionEvent event) {
        TicketsWindowController controller = WindowFactory.getFactory().getController(WindowConfig.TickectsWindow);
        controller.setEmail(email);

        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.TickectsWindow);
        clearFields();
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
    private void initialize() {
        if (email == null) {
            return;
        }

        accountDto = ClientServicesFactory.getFactory().getLoadAccountByEmail().load(email);

        nameField.setText(accountDto.getName());
        surnameField.setText(accountDto.getSurname());
        emailField.setText(accountDto.getEmail());
        passwordField.setText(accountDto.getPassword());
        phoneNumberField.setText(accountDto.getPhoneNumber());
        initBirthdayField();
    }

    @FXML
    void saveButtonPressed(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (!AccountUtils.checkPasswordFormat(password) || !AccountUtils.checkEmailFormat(email)) {
            WindowFactory.getFactory().showStage(WindowConfig.WrongEmailOrPasswordWindow);
            return;
        }

        if (!accountDto.getEmail().equals(email) & ClientServicesFactory.getFactory().getCheckAccountExistenceService().check(email)) {
            WindowFactory.getFactory().showStage(WindowConfig.WrongEmailOrPasswordWindow);
            return;
        }

        String text = birthdayField.getEditor().getText();
        birthdayField.setValue(birthdayField.getConverter().fromString(text));
        LocalDate birthdayLocalDate = birthdayField.getValue();
        if (birthdayLocalDate == null) {
            return;
        }

        accountDto.setEmail(email);
        accountDto.setPassword(password);
        accountDto.setName(nameField.getText());
        accountDto.setSurname(surnameField.getText());
        accountDto.setPhoneNumber(phoneNumberField.getText());
        accountDto.setDateOfBirth(birthdayLocalDate);

        ClientServicesFactory.getFactory().getUpdateAccountService().update(accountDto);
    }

    @FXML
    void deleteButtonPressed(ActionEvent event) {
        int id = accountDto.getId();
        ClientServicesFactory.getFactory().getDeleteAccountById().delete(id);

        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
        clearFields();
    }

    @FXML
    private void logOut(){
        clearFields();
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
    }

    private void clearFields() {
        nameField.clear();
        surnameField.clear();
        emailField.clear();
        passwordField.clear();
        phoneNumberField.clear();
        birthdayField.getEditor().clear();
        email = null;
        accountDto = null;
    }

    private void initBirthdayField(){
        birthdayField.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate object) {
                if (object == null) {
                    return "";
                }
                String format = object.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                return format;
            }

            @Override
            public LocalDate fromString(String string) {
                LocalDate date = null;
                try {
                    date = LocalDate.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } catch (DateTimeParseException e) {
                    birthdayField.getEditor().setText("Wrong format, expected date format DD-MM-YYYY");
                }
                return date;
            }
        });

        birthdayField.setValue(accountDto.getDateOfBirth());
    }

}

