package com.college.view.controllers;

import com.college.controller.ContractController;
import com.college.model.entity.*;
import com.college.view.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConfirmationController {
    @FXML private Button confirmationButton;
    @FXML private Label fullUserName;
    @FXML private Label email;
    @FXML private Label dealerName;
    @FXML private Label dealerAddress;
    @FXML private Label automobileMark;
    @FXML private Label equipmentName;
    @FXML private Label equipmentPrice;
    @FXML private Label warrantyName;
    @FXML private Label warrantyPrice;
    @FXML private Label totalPrice;
    @FXML private ImageView carImageView;

    private ContractController contractController;

    @FXML
    private void initialize() {
        this.contractController = ControllerManager.getContractController();

        User user = ControllerManager.getAuthorizationController().getCurrentUser();
        Dealer dealer = ContractBuilder.getDealer();
        Warranty warranty = ContractBuilder.getWarranty();
        Equipment equipment = ContractBuilder.getEquipment();

        try {
            carImageView.setImage(new Image(equipment.getImagePath()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        fullUserName.setText(user.getName() + " " + user.getSurname());
        email.setText(user.getEmail());

        dealerName.setText(dealer.getName());
        dealerAddress.setText(dealer.getAddress());

        automobileMark.setText(equipment.getAutomobile().getMark() + " " + equipment.getAutomobile().getModel());
        equipmentName.setText(equipment.getName());
        equipmentPrice.setText(equipment.getPrice() + "$");

        warrantyName.setText(warranty.getName());
        warrantyPrice.setText(warranty.getPrice() + "$");

        totalPrice.setText("Total: " + (equipment.getPrice() + warranty.getPrice()) + "$");
    }



    public void onConfirmButtonClicked(ActionEvent actionEvent) {
        boolean confirmed = AlertHelper.showConfirmationDialog("Are you sure that you want buy this automobile?");
        if (!confirmed) {
            return;
        }

        Contract contract = ContractBuilder.buildContract();
        boolean saved = contractController.saveContract(contract);
        if (saved) {
            AlertHelper.showSimpleAlertDialog("Saving success", "Your transaction is confirmed! Congratulations, you bought an automobile! ", Alert.AlertType.INFORMATION);
            SceneRouterService.getSceneRouter().clearStack();
            SceneRouterService.getSceneRouter().switchTo("home-form.fxml", AnimationType.ZOOM);
        } else {
            AlertHelper.showSimpleAlertDialog("Error", "Opssssss, something went wrong!", Alert.AlertType.ERROR);
        }
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
