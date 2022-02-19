package com.unknown.supportapp.manager.ui.guiWindows.ticketsWindow;

import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import com.unknown.supportapp.common.dto.ticket.TicketDto;
import com.unknown.supportapp.manager.ui.factory.WindowConfig;
import com.unknown.supportapp.manager.ui.factory.WindowFactory;
import com.unknown.supportapp.manager.ui.guiWindows.managedTicketsWindow.ManagedTicketsWindowController;
import com.unknown.supportapp.manager.ui.guiWindows.unassignedTicketWindow.UnassignedTicketWindowController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.List;

public class TicketsWindowController {

    private String email;

    @FXML
    private TableColumn<TicketDto, String> modelColumn;

    @FXML
    private TableView<TicketDto> ticketsTable;

    @FXML
    private TableColumn<TicketDto, String> themeColumn;

    @FXML
    private void initialize() {
        ticketsTable.setRowFactory(tv -> {
            TableRow<TicketDto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && (!row.isEmpty())) {
                    TicketDto item = row.getItem();

                    UnassignedTicketWindowController controller = WindowFactory.getFactory().getController(WindowConfig.UnassignedTicketWindow);
                    controller.setTicket(item);
                    controller.setEmail(email);
                    WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.UnassignedTicketWindow);
                }
            });
            return row;
        });
        themeColumn.setCellValueFactory(new PropertyValueFactory<>("theme"));
        modelColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TicketDto, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TicketDto, String> param) {
                String model = ClientServicesFactory.getFactory().getOwnedProductLoadModelByIdService().load(param.getValue().getProductId());
                return new SimpleStringProperty(model);
            }
        });

        List<TicketDto> load = ClientServicesFactory.getFactory().getLoadUnAssignedTicketsService().load();
        ObservableList<TicketDto> ticketDtos = FXCollections.observableArrayList(load);
        ticketsTable.setItems(ticketDtos);
    }

    @FXML
    void managedTicketsButtonPressed(ActionEvent event) {
        ManagedTicketsWindowController controller = WindowFactory.getFactory().getController(WindowConfig.ManagedTicketsWindow);
        controller.setEmail(email);
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.ManagedTicketsWindow);
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


    public void setEmail(String email) {
        this.email = email;
        initialize();
    }

    public void refresh(){
        List<TicketDto> load = ClientServicesFactory.getFactory().getLoadUnAssignedTicketsService().load();
        ObservableList<TicketDto> ticketDtos = FXCollections.observableArrayList(load);
        ticketsTable.setItems(ticketDtos);
    }

    @FXML
    void logOut(ActionEvent event) {
        email = null;
        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.LoginWindow);
    }

}