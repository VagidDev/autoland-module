package com.college.view.controllers;

import com.college.controller.validators.user.UserValidationResponse;
import com.college.model.entity.Dealer;
import com.college.model.entity.User;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import com.college.controller.DealerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.Date;

public class AddUpdateDealerController {
    @FXML private Label titleLabel;
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField telephoneField;
    @FXML private TextField faxField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private DealerController dealerController;

    public void initialize() {
        dealerController = ControllerManager.getDealerController();
        if (AdminPanelContext.getDealerID() != -1) {
            titleLabel.setText("Update Dealer");

            Dealer dealer = dealerController.getDealerById(AdminPanelContext.getDealerID());
            nameField.setText(dealer.getName());
            addressField.setText(dealer.getAddress());
            telephoneField.setText(dealer.getTelephone());
            faxField.setText(dealer.getFax());
        }
    }

    public void onSave(ActionEvent actionEvent) {
        Dealer dealer = null;
        if (AdminPanelContext.getDealerID() == -1) {
            dealer = new Dealer();
        } else {
            dealer = dealerController.getDealerById(AdminPanelContext.getDealerID());
        }

        dealer.setName(nameField.getText());
        dealer.setAddress(addressField.getText());
        dealer.setTelephone(telephoneField.getText());
        dealer.setFax(faxField.getText());


        if (AdminPanelContext.getDealerID() == -1) {
            dealerController.createDealer(dealer);
            AlertHelper.showSimpleAlertDialog("Success", "New dealer was created!", Alert.AlertType.INFORMATION);
        } else {
            dealerController.editDealer(dealer);
            AlertHelper.showSimpleAlertDialog("Success", "New dealer was updated!", Alert.AlertType.INFORMATION);
        }
        AdminPanelContext.setDealerID(-1);
        ((Stage) saveButton.getScene().getWindow()).close();
    }

    public void onCancel(ActionEvent actionEvent) {
        AdminPanelContext.setDealerID(-1);
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

}
