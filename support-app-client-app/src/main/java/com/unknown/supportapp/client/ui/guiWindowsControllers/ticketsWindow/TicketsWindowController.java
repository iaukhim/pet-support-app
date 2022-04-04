package com.unknown.supportapp.client.ui.guiWindowsControllers.ticketsWindow;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindowsControllers.productsWindow.ProductsWindowController;
import com.unknown.supportapp.client.ui.guiWindowsControllers.profileWindow.ProfileWindowController;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.common.dto.ticket.TicketDto;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class TicketsWindowController {

    private String email;

    private ObservableList<TicketDto> openedTickets;

    private ObservableList<TicketDto> closedTickets;

    private ObservableList<OwnedProductDto> ownedProducts;

    private boolean creation = false;

    private boolean addInfo = false;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField themeField;

    @FXML
    private Label themeLabel;

    @FXML
    private Button addInfoButton;

    @FXML
    private Button closeTicketButton;

    @FXML
    private ComboBox<TicketDto> closedTicketsBox;

    @FXML
    private ComboBox<TicketDto> openedTicketsBox;

    @FXML
    private ComboBox<OwnedProductDto> productBox;

    @FXML
    private void initialize() {
        if (email == null) {
            return;
        }
        initProductsBox();
        initTicketsBoxes();
    }


    @FXML
    void openedTicketSelected(ActionEvent event) {
        if (openedTicketsBox.getValue() == null) {
            return;
        }

        creation = false;

        TicketDto selectedTicket = openedTicketsBox.getValue();

        themeField.setText(selectedTicket.getTheme());
        textArea.setText(selectedTicket.getText());

        themeField.setVisible(true);
        themeField.setEditable(false);
        themeLabel.setVisible(true);
        textArea.setVisible(true);
        textArea.setEditable(false);
        addInfoButton.setVisible(true);
        closeTicketButton.setVisible(true);
        productBox.setVisible(true);
        productBox.setDisable(true);
        closedTicketsBox.getSelectionModel().clearSelection();

        for (OwnedProductDto productDto : ownedProducts) {
            if (productDto.getId() == selectedTicket.getProductId()) {
                productBox.setValue(productDto);
                return;
            }
        }
    }

    @FXML
    void closedTicketSelected(ActionEvent event) {
        if (closedTicketsBox.getValue() == null) {
            return;
        }
        productBox.setDisable(true);
        creation = false;

        TicketDto selectedTicket = closedTicketsBox.getValue();
        themeField.setText(selectedTicket.getTheme());
        textArea.setText(selectedTicket.getText());

        themeField.setVisible(true);
        themeField.setEditable(false);
        themeLabel.setVisible(true);
        textArea.setVisible(true);
        textArea.setEditable(false);
        productBox.setVisible(false);
        addInfoButton.setVisible(false);
        closeTicketButton.setVisible(false);
        openedTicketsBox.getSelectionModel().clearSelection();

        productBox.setVisible(true);
        for (OwnedProductDto productDto : ownedProducts) {
            if (productDto.getId() == selectedTicket.getProductId()) {
                productBox.setValue(productDto);
                return;
            }
        }
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
    void addInfoButtonPressed(ActionEvent event) {
        try {
            if (addInfo) {
                if (themeField.getText().trim().length() == 0 || textArea.getText().trim().length() == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all fields");
                    alert.show();
                    return;
                }

                TicketDto ticketDto = openedTicketsBox.getValue();
                ticketDto.setText(ticketDto.getText() + "\n -------------- \n" + textArea.getText());
                ClientServicesFactory.getFactory().getUpdateTicketService().update(ticketDto);

                textArea.setEditable(false);
                textArea.clear();
                themeField.clear();
                openedTicketsBox.getSelectionModel().clearSelection();
                closedTicketsBox.getSelectionModel().clearSelection();
                addInfo = false;
                int userId = ClientServicesFactory.getFactory().getLoadIdByEmailService().load(email);
                List<TicketDto> opened = ClientServicesFactory.getFactory().getLoadUserOpenedTicketsService().load(userId);
                openedTickets.clear();
                openedTickets.addAll(opened);
                openedTicketsBox.setItems(FXCollections.observableArrayList(openedTickets));
                openedTicketsBox.getSelectionModel().clearSelection();
                themeField.clear();
                textArea.clear();
            }

            textArea.clear();

            textArea.setVisible(true);
            textArea.setEditable(true);
            themeField.setVisible(true);
            themeField.setEditable(false);
            themeLabel.setVisible(true);
            addInfoButton.setVisible(true);
            addInfo = true;
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    @FXML
    void createNewTicket(ActionEvent event) {
        try {
            if(ownedProducts.size() == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You don`t have any products yet");
                alert.show();
                return;
            }
            productBox.setDisable(false);
            if (creation) {
                if (themeField.getText().trim().length() == 0 || textArea.getText().trim().length() == 0 || productBox.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill all fields");
                    alert.show();
                    return;
                }

                TicketDto ticketDto = new TicketDto();
                ticketDto.setTheme(themeField.getText());
                ticketDto.setText(textArea.getText());
                ticketDto.setStatus(true);
                int id = ClientServicesFactory.getFactory().getLoadIdByEmailService().load(email);
                ticketDto.setStarterId(id);
                int productId = productBox.getValue().getId();
                ticketDto.setProductId(productId);
                ClientServicesFactory.getFactory().getSaveNewTicketService().save(ticketDto);
                textArea.setEditable(false);
                themeField.setEditable(false);
                int userId = ClientServicesFactory.getFactory().getLoadIdByEmailService().load(email);
                List<TicketDto> opened = ClientServicesFactory.getFactory().getLoadUserOpenedTicketsService().load(userId);
                openedTickets.clear();
                openedTickets.addAll(opened);
                openedTicketsBox.setItems(FXCollections.observableArrayList(openedTickets));
                openedTicketsBox.getSelectionModel().clearSelection();
                themeField.clear();
                textArea.clear();
            }

            textArea.clear();
            themeField.clear();

            textArea.setVisible(true);
            textArea.setEditable(true);
            themeField.setVisible(true);
            themeField.setEditable(true);
            themeLabel.setVisible(true);
            productBox.setVisible(true);
            addInfoButton.setVisible(false);
            closeTicketButton.setVisible(false);
            creation = true;
            openedTicketsBox.getSelectionModel().clearSelection();
            closedTicketsBox.getSelectionModel().clearSelection();
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    @FXML
    private void logOut(){
        clearFields();
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
    }

    public void setEmail(String email) {
        this.email = email;
        initialize();
    }

    private void initTicketsBoxes() {
        try {
            int userId = ClientServicesFactory.getFactory().getLoadIdByEmailService().load(email);

            List<TicketDto> opened = ClientServicesFactory.getFactory().getLoadUserOpenedTicketsService().load(userId);
            List<TicketDto> closed = ClientServicesFactory.getFactory().getLoadUserClosedTicketsService().load(userId);


            openedTickets = FXCollections.observableArrayList(opened);
            closedTickets = FXCollections.observableArrayList(closed);

            openedTicketsBox.setItems(openedTickets);
            closedTicketsBox.setItems(this.closedTickets);

            openedTicketsBox.setConverter(new StringConverter<TicketDto>() {
                @Override
                public String toString(TicketDto object) {
                    if (object == null) {
                        return "";
                    }
                    return object.getTheme();
                }

                @Override
                public TicketDto fromString(String string) {
                    return null;
                }
            });

            closedTicketsBox.setConverter(new StringConverter<TicketDto>() {
                @Override
                public String toString(TicketDto object) {

                    if (object == null) {
                        return "";
                    }
                    return object.getTheme();
                }

                @Override
                public TicketDto fromString(String string) {
                    return null;
                }
            });
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    private void initProductsBox() {
        try {
            List<OwnedProductDto> tmp = ClientServicesFactory.getFactory().getLoadUsersProductsService().load(email);
            ArrayList<OwnedProductDto> ownedProductDtos = new ArrayList<>();
            for (OwnedProductDto product : tmp) {
                boolean passed = true;
                for (OwnedProductDto dto : ownedProductDtos) {
                    if (product.getModel().equals(dto.getModel())) {
                        passed = false;
                    }
                }
                if (passed) {
                    ownedProductDtos.add(product);
                }
            }

            ownedProducts = FXCollections.observableArrayList(ownedProductDtos);
            productBox.setItems(ownedProducts);
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
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    @FXML
    void closeTicketButtonPressed(ActionEvent event) {
        try {
            TicketDto ticket = openedTicketsBox.getValue();
            ClientServicesFactory.getFactory().getCloseTicketService().close(ticket.getId());
            int userId = ClientServicesFactory.getFactory().getLoadIdByEmailService().load(email);
            List<TicketDto> opened = ClientServicesFactory.getFactory().getLoadUserOpenedTicketsService().load(userId);
            openedTickets.clear();
            openedTickets.addAll(opened);
            openedTicketsBox.setItems(FXCollections.observableArrayList(openedTickets));
            openedTicketsBox.getSelectionModel().clearSelection();
            themeField.clear();
            textArea.clear();

            List<TicketDto> closed = ClientServicesFactory.getFactory().getLoadUserClosedTicketsService().load(userId);
            closedTickets.clear();
            closedTickets.setAll(closed);
            closedTicketsBox.setItems(closedTickets);
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    private void clearFields(){
        ownedProducts = null;
        openedTickets = null;
        closedTickets = null;
        themeField.clear();
        textArea.clear();
        email = null;
        creation = false;
        addInfo = false;
    }
}