package com.college.view.controllers;

import com.college.controller.ContractController;
import com.college.model.entity.*;
import com.college.view.core.ContractBuilder;
import com.college.view.core.ControllerManager;
import com.college.view.core.StageService;
import javafx.scene.control.ButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Optional;

public class ConfirmationController {
    @FXML
    private Button confirmationButton;
    @FXML
    private Label fullUserName;
    @FXML
    private Label email;
    @FXML
    private Label dealerName;
    @FXML
    private Label dealerAddress;
    @FXML
    private Label automobileMark;
    @FXML
    private Label equipmentName;
    @FXML
    private Label equipmentPrice;
    @FXML
    private Label warrantyName;
    @FXML
    private Label warrantyPrice;
    @FXML
    private Label totalPrice;

    private ContractController contractController;

    @FXML
    private void initialize() {
        this.contractController = ControllerManager.getContractController();

        User user = ControllerManager.getAuthorizationController().getCurrentUser();
        Dealer dealer = ContractBuilder.getDealer();
        Warranty warranty = ContractBuilder.getWarranty();
        Equipment equipment = ContractBuilder.getEquipment();

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

    private boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void showSaveAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public void onConfirmButtonClicked(ActionEvent actionEvent) {
        boolean confirmed = showConfirmationDialog("Вы уверенны, что хотите совершить покупку?");
        if (!confirmed) {
            return;
        }

        Contract contract = ContractBuilder.buildContract();
        boolean saved = contractController.saveContract(contract);
        if (saved) {
            showSaveAlert("Сохранение", "Покупка прошла успешно! Ваш автомобиль уже вас ждет!", Alert.AlertType.INFORMATION);
            StageService.closeStageAndClearStack();
            StageService.buildAndShowStage("Home", "home-form.fxml");
        } else {
            showSaveAlert("Ошибка", "Ошибка покупки!!!!", Alert.AlertType.ERROR);
        }
    }

    public void onCancelButtonClicked(ActionEvent actionEvent) {
        StageService.closeStageAndOpenPrevious();
    }
}
