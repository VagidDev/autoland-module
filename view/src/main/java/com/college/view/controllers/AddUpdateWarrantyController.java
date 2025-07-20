package com.college.view.controllers;

import com.college.controller.WarrantyController;
import com.college.controller.validators.warranty.WarrantyValidationResponse;
import com.college.model.entity.Dealer;
import com.college.model.entity.Warranty;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import com.college.view.core.TextFilters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AddUpdateWarrantyController {
    @FXML private Label titleLabel;
    @FXML private TextField nameField;
    @FXML private TextField durationField;
    @FXML private TextField priceField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private WarrantyController warrantyController;

    public void initialize() {
        warrantyController = ControllerManager.getWarrantyController();
        if (AdminPanelContext.getWarrantyID() != -1) {
            titleLabel.setText("Update Warranty");
            Warranty warranty = warrantyController.getWarranty(AdminPanelContext.getWarrantyID());
            nameField.setText(warranty.getName());
            durationField.setText(String.valueOf(warranty.getDuration()));
            priceField.setText(String.valueOf(warranty.getPrice()));
        }

        TextFormatter<String> integerFormatter = new TextFormatter<>(TextFilters.INTEGER_NUMBER_FILTER);
        TextFormatter<String> doubleFormatter = new TextFormatter<>(TextFilters.DOUBLE_NUMBER_FILTER);

        durationField.setTextFormatter(integerFormatter);
        priceField.setTextFormatter(doubleFormatter);
    }

    public void onSave(ActionEvent actionEvent) {
        Warranty warranty = null;
        if (AdminPanelContext.getWarrantyID() == -1) {
            warranty = new Warranty();
        } else {
            warranty = warrantyController.getWarranty(AdminPanelContext.getWarrantyID());
        }

        warranty.setName(nameField.getText());
        if (durationField.getText().isEmpty()) warranty.setDuration(0); else warranty.setDuration(Integer.parseInt(durationField.getText()));
        if (priceField.getText().isEmpty()) warranty.setPrice(0); else warranty.setPrice(Double.parseDouble(priceField.getText()));

        WarrantyValidationResponse response = warrantyController.validateWarranty(warranty);
        if (response != WarrantyValidationResponse.VALID) {
            AlertHelper.invalidWarrantyDataAlert(response);
            return;
        }

        if (AdminPanelContext.getWarrantyID() == -1) {
            warrantyController.createWarranty(warranty);
            AlertHelper.showSimpleAlertDialog("Success", "New warranty was created!", Alert.AlertType.INFORMATION);
        } else {
            warrantyController.editWarranty(warranty);
            AlertHelper.showSimpleAlertDialog("Success", "New warranty was updated!", Alert.AlertType.INFORMATION);
        }
        AdminPanelContext.setWarrantyID(-1);
        ((Stage) saveButton.getScene().getWindow()).close();
    }

    public void onCancel(ActionEvent actionEvent) {
        AdminPanelContext.setWarrantyID(-1);
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

}
