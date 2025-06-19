package com.college.view.controllers;

import com.college.controller.WarrantyController;
import com.college.model.entity.Dealer;
import com.college.model.entity.Warranty;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    }

    public void onSave(ActionEvent actionEvent) {
        Warranty warranty = null;
        if (AdminPanelContext.getWarrantyID() == -1) {
            warranty = new Warranty();
        } else {
            warranty = warrantyController.getWarranty(AdminPanelContext.getWarrantyID());
        }

        warranty.setName(nameField.getText());
        warranty.setDuration(Integer.parseInt(durationField.getText()));
        warranty.setPrice(Double.parseDouble(priceField.getText()));

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
