package com.unknown.supportapp.manager.ui.guiWindowsControllers.unassignedTicketWindow;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.common.dto.ticket.TicketDto;
import com.unknown.supportapp.manager.ui.factory.WindowFactory;
import com.unknown.supportapp.manager.ui.guiWindowsControllers.ticketsWindow.TicketsWindowController;
import com.unknown.supportapp.manager.ui.factory.WindowConfig;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;


public class UnassignedTicketWindowController {

    private TicketDto ticket;

    private String email;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField themeField;

    @FXML
    private ComboBox<OwnedProductDto> productBox;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
        initialize();
    }

    @FXML
    private void initialize() {
        if (ticket == null) {
            return;
        }
        initProductsBox();
        themeField.setText(ticket.getTheme());
        textArea.setText(ticket.getText());
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

    private void initProductsBox() {
        try {
            productBox.setConverter(new StringConverter<OwnedProductDto>() {
                @Override
                public String toString(OwnedProductDto object) {
                    if (object == null) {
                        return "";
                    }
                    return object.getModel();
                }

                @Override
                public OwnedProductDto fromString(String string) {
                    return null;
                }
            });
            OwnedProductDto product = ClientServicesFactory.getFactory().getLoadOwnedProductByIdService().load(ticket.getProductId());
            productBox.setItems(FXCollections.observableArrayList(product));
            productBox.getSelectionModel().selectFirst();
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    @FXML
    void manageTicketButtonPressed(ActionEvent event) {
        try {
            int id = ClientServicesFactory.getFactory().getManagerLoadIdByEmailService().load(email);
            ticket.setManagerId(id);
            ClientServicesFactory.getFactory().getTicketSetManagerIdService().set(ticket);
            ticketsButtonPressed(new ActionEvent());
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }


    @FXML
    void ticketsButtonPressed(ActionEvent event) {
        TicketsWindowController controller = WindowFactory.getFactory().getController(WindowConfig.TicketsWindow);
        controller.refresh();
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.TicketsWindow);
    }

    @FXML
    void logOut(ActionEvent event) {
        ticket = null;
        email = null;
        themeField.clear();
        textArea.clear();
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
    }

}