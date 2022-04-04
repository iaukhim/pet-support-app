package com.unknown.supportapp.client.ui.guiWindowsControllers.newProductWindow;

import com.unknown.supportapp.client.common.exception.CustomServerError;
import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.common.dto.product.ProductDto;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.util.List;

public class NewProductWindowController {


    private String email;

    private String currentType;

    @FXML
    private AnchorPane LoginWindowPane;

    @FXML
    private Button addButton;

    @FXML
    private ChoiceBox<ProductDto> modelBox;

    @FXML
    private TextField serialField;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    void addButtonPressed(ActionEvent event) {
        try {
            boolean check = ClientServicesFactory.getFactory().getCheckSerialService().check(serialField.getText());

            if (!check){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Check serial number format");
                alert.showAndWait();
                return;
            }

            OwnedProductDto productDto = new OwnedProductDto();

            int id = ClientServicesFactory.getFactory().getLoadIdByEmailService().load(email);

            productDto.setType(typeBox.getValue());
            productDto.setModel(modelBox.getConverter().toString(modelBox.getValue()));
            productDto.setSerialNumber(serialField.getText());
            productDto.setOwnerId(id);

            ClientServicesFactory.getFactory().getSaveProductService().save(productDto);

            serialField.clear();
            modelBox.getSelectionModel().clearSelection();
            typeBox.getSelectionModel().clearSelection();
            WindowFactory.getFactory().hideStage(WindowConfig.NewProductWindow);
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }

    }

    @FXML
    private void initialize() {

        try {
            List<String> productTypes = ClientServicesFactory.getFactory().getLoadAllProductsTypesService().load();
            ObservableList<String> productsTypesList = FXCollections.observableArrayList(productTypes);
            typeBox.setItems(productsTypesList);
            typeBox.setOnAction(this::typeSelected);

            modelBox.setConverter(new StringConverter<ProductDto>() {
                @Override
                public String toString(ProductDto object) {
                    if (object == null) {
                        return "";
                    }
                    return object.getModel();
                }

                @Override
                public ProductDto fromString(String string) {
                    return new ProductDto();
                }
            });
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    private void typeSelected(ActionEvent event) {
        try {
            if(currentType == null){
                currentType = "";
            }
            if (currentType.equals(typeBox.getValue())) {
                return;
            }
            currentType = typeBox.getValue();
            List<ProductDto> products = ClientServicesFactory.getFactory().getLoadProductsByTypeService().load(currentType);
            ObservableList<ProductDto> productsList = FXCollections.observableArrayList(products);
            modelBox.setItems(productsList);
        } catch (CustomServerError e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(e.getErrorTitle());
            alert.setContentText(e.getErrorDescription());
            alert.show();
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

