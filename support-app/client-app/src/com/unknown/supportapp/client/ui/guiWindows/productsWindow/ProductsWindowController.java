package com.unknown.supportapp.client.ui.guiWindows.productsWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.client.ui.guiWindows.editProductWindow.EditProductWindowController;
import com.unknown.supportapp.client.ui.guiWindows.newProductWindow.NewProductWindowController;
import com.unknown.supportapp.client.ui.guiWindows.profileWindow.ProfileWindowController;
import com.unknown.supportapp.client.ui.guiWindows.ticketsWindow.TicketsWindowController;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class ProductsWindowController{

    private String email;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<OwnedProductDto> productsTable;

    @FXML
    private TableColumn<OwnedProductDto, String> modelColumn;

    @FXML
    private TableColumn<OwnedProductDto, String> typeColumn;

    @FXML
    private TableColumn<OwnedProductDto, String> serialColumn;

    public void setEmail(String email) {
        this.email = email;
        initializeWindow();
    }

    @FXML
    void ticketsButtonPressed(ActionEvent event) {
        TicketsWindowController controller = WindowFactory.getFactory().getController(WindowConfig.TickectsWindow);
        controller.setEmail(email);

        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.TickectsWindow);
    }

    @FXML
    void profileButtonPressed(ActionEvent event) {
        ProfileWindowController controller = WindowFactory.getFactory().getController(WindowConfig.ProfileWindow);
        controller.setEmail(email);

        WindowFactory.getFactory().setScene(WindowConfig.PrimaryWindow, WindowConfig.ProfileWindow);
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
    void addProductButtonPressed(ActionEvent event) {
        NewProductWindowController controller = WindowFactory.getFactory().getController(WindowConfig.NewProductWindow);
        controller.setEmail(email);
        WindowFactory.getFactory().showStage(WindowConfig.NewProductWindow);

    }

    @FXML
    private void initializeWindow() {
        initContextMenu();
        initTable();
    }

    private void contextDelete(ActionEvent event){
        OwnedProductDto selectedItem = productsTable.getSelectionModel().getSelectedItem();
        ClientServicesFactory.getFactory().getDeleteUserProduct().delete(selectedItem);
        initializeWindow();
    }

    private void contextUpdate(ActionEvent event){
        OwnedProductDto selectedItem = productsTable.getSelectionModel().getSelectedItem();

        Stage stage = WindowFactory.getFactory().getStage(WindowConfig.EditProductWindow);
        EditProductWindowController controller = WindowFactory.getFactory().getController(WindowConfig.EditProductWindow);
        controller.setProductDto(selectedItem);

        stage.show();
        productsTable.getSelectionModel().clearSelection();
    }

    private void initContextMenu(){
        MenuItem update = new MenuItem("Update");
        update.setOnAction(this::contextUpdate);
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(this::contextDelete);
        contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(update, delete);
    }

    private void initTable(){
        productsTable.prefWidthProperty().bind(mainPane.widthProperty());
        productsTable.setRowFactory(tv -> {
            TableRow<OwnedProductDto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && (!row.isEmpty())) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));

        List<OwnedProductDto> loadedProducts = ClientServicesFactory.getFactory().getLoadUsersProductsService().load(email);
        ObservableList<OwnedProductDto> productDtos = FXCollections.observableArrayList(loadedProducts);
        productsTable.setItems(productDtos);
    }
}

