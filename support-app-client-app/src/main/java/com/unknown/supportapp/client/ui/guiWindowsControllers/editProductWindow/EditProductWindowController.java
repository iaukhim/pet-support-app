package com.unknown.supportapp.client.ui.guiWindowsControllers.editProductWindow;

import com.unknown.supportapp.client.ui.factory.WindowConfig;
import com.unknown.supportapp.client.ui.factory.WindowFactory;
import com.unknown.supportapp.common.dto.ownedProduct.OwnedProductDto;
import com.unknown.supportapp.client.common.service.factory.ClientServicesFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditProductWindowController {

    private OwnedProductDto productDto;

    public void setProductDto(OwnedProductDto productDto) {
        this.productDto = productDto;
        init();
    }

    @FXML
    private Label errorLabel;

    @FXML
    private TextField modelField;

    @FXML
    private TextField serialField;

    @FXML
    private TextField typeField;

    @FXML
    public void editButtonPressed(){
        if(serialField.getText().length() == 0){
            errorLabel.setVisible(true);
            return;
        }
        errorLabel.setVisible(false);

        boolean result = ClientServicesFactory.getFactory().getChangeSerialService().change(productDto.getSerialNumber(), serialField.getText());

        if (!result){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Check serial number format");
            alert.showAndWait();
            return;
        }

        serialField.clear();
        modelField.clear();
        typeField.clear();
        WindowFactory.getFactory().hideStage(WindowConfig.EditProductWindow);
    }

    private void init(){
        typeField.setText(productDto.getType());
        modelField.setText(productDto.getModel());
        serialField.setText(productDto.getSerialNumber());
    }
}


