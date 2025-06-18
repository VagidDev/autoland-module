package com.college.view.controllers;

import com.college.controller.AutomobileController;
import com.college.controller.EquipmentController;
import com.college.controller.UserController;
import com.college.controller.DealerController;
import com.college.controller.WarrantyController;
import com.college.model.entity.*;
import com.college.view.core.ControllerManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class AddUpdateContractController {
    @FXML private ComboBox<User> userComboBox;
    @FXML private ComboBox<Dealer> dealerComboBox;
    @FXML private ComboBox<Automobile> automobileComboBox;
    @FXML private ComboBox<Equipment> equipmentComboBox;
    @FXML private ComboBox<Warranty> warrantyComboBox;
    @FXML private DatePicker conclusionDatePicker;

    private UserController userController;
    private DealerController dealerController;
    private AutomobileController automobileController;
    private EquipmentController equipmentController;
    private WarrantyController warrantyController;

    public void initialize() {
        userController = ControllerManager.getUserController();
        dealerController = ControllerManager.getDealerController();
        automobileController = ControllerManager.getAutomobileController();
        equipmentController = ControllerManager.getEquipmentController();
        warrantyController = ControllerManager.getWarrantyController();
        loadUserComboBox();
        loadDealerComboBox();
        loadAutomobileComboBox();
        loadWarrantyComboBox();

    }

    private void loadUserComboBox() {
        userComboBox.setItems(FXCollections.observableList(userController.getUsers()));
        userComboBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User user) {
                return user == null ? "" : user.getLogin();
            }

            @Override
            public User fromString(String s) {
                return userController
                        .getUsers().stream()
                        .filter(user -> user.getLogin().equals(s))
                        .findFirst().orElse(null);
            }
        });
    }

    private void loadDealerComboBox() {
        dealerComboBox.setItems(FXCollections.observableList(dealerController.getAllDealers()));
        dealerComboBox.setConverter(new StringConverter<Dealer>() {
            @Override
            public String toString(Dealer dealer) {
                return dealer.getName();
            }

            @Override
            public Dealer fromString(String s) {
                return dealerController
                        .getAllDealers().stream()
                        .filter(dealer ->  dealer.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });
    }

    private void loadAutomobileComboBox() {
        automobileComboBox.setItems(FXCollections.observableList(automobileController.getAllAutomobiles()));
        automobileComboBox.setConverter(new StringConverter<Automobile>() {
            @Override
            public String toString(Automobile automobile) {
                if (automobile == null) {
                    return null;
                }
                return automobile.getMark() + " " + automobile.getModel();
            }

            @Override
            public Automobile fromString(String s) {
                return automobileController
                        .getAllAutomobiles().stream()
                        .filter(automobile -> {
                            String searchString = automobile.getMark() + " " + automobile.getModel();
                            return searchString.equals(s);
                        }).findFirst().orElse(null);
            }
        });
        automobileComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                loadEquipmentComboBox(newValue);
            }
        });
    }

    private void loadEquipmentComboBox(Automobile automobile) {
        equipmentComboBox.setItems(FXCollections.observableList(equipmentController.getEquipmentByAutomobile(automobile)));
        equipmentComboBox.setConverter(new StringConverter<Equipment>() {
            @Override
            public String toString(Equipment equipment) {
                return equipment.getName();
            }

            @Override
            public Equipment fromString(String s) {
                return equipmentController
                        .getEquipmentByAutomobile(automobile).stream()
                        .filter(equipment ->  equipment.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });
    }

    private void loadWarrantyComboBox() {
        warrantyComboBox.setItems(FXCollections.observableList(warrantyController.getAllWarranty()));
        warrantyComboBox.setConverter(new StringConverter<Warranty>() {
            @Override
            public String toString(Warranty warranty) {
                return warranty.getName();
            }

            @Override
            public Warranty fromString(String s) {
                return warrantyController
                        .getAllWarranty().stream()
                        .filter(warranty -> warranty.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });
    }
}
