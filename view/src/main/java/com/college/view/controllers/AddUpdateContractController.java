package com.college.view.controllers;

import com.college.controller.*;
import com.college.controller.DealerController;
import com.college.controller.WarrantyController;
import com.college.model.entity.*;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.ZoneId;
import java.util.Date;

public class AddUpdateContractController {
    @FXML private Label titleLabel;
    @FXML private ComboBox<User> userComboBox;
    @FXML private ComboBox<Dealer> dealerComboBox;
    @FXML private ComboBox<Automobile> automobileComboBox;
    @FXML private ComboBox<Equipment> equipmentComboBox;
    @FXML private ComboBox<Warranty> warrantyComboBox;
    @FXML private DatePicker conclusionDatePicker;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private UserController userController;
    private DealerController dealerController;
    private AutomobileController automobileController;
    private EquipmentController equipmentController;
    private WarrantyController warrantyController;
    private ContractController contractController;

    public void initialize() {
        userController = ControllerManager.getUserController();
        dealerController = ControllerManager.getDealerController();
        automobileController = ControllerManager.getAutomobileController();
        equipmentController = ControllerManager.getEquipmentController();
        warrantyController = ControllerManager.getWarrantyController();
        contractController = ControllerManager.getContractController();

        if (AdminPanelContext.getContractID() != -1) {
            titleLabel.setText("Update Contract");

            Contract contract = contractController.getContract(AdminPanelContext.getContractID());
            userComboBox.getSelectionModel().select(contract.getUser());
            dealerComboBox.getSelectionModel().select(contract.getDealer());
            automobileComboBox.getSelectionModel().select(contract.getEquipment().getAutomobile());
            equipmentComboBox.getSelectionModel().select(contract.getEquipment());
            warrantyComboBox.getSelectionModel().select(contract.getWarranty());
            conclusionDatePicker.setValue(contract.getConclusionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            loadEquipmentComboBox(contract.getEquipment().getAutomobile());
        }

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

    public void onSave(ActionEvent actionEvent) {
        Contract contract = null;
        if (AdminPanelContext.getContractID() == -1) {
            contract = new Contract();
        } else {
            contract = contractController.getContract(AdminPanelContext.getContractID());
        }

        Date conclusionDate = conclusionDatePicker.getValue() == null ? new Date()
                : Date.from(conclusionDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        contract.setUser(userComboBox.getSelectionModel().getSelectedItem());
        contract.setDealer(dealerComboBox.getSelectionModel().getSelectedItem());
        contract.setEquipment(equipmentComboBox.getSelectionModel().getSelectedItem());
        contract.setWarranty(warrantyComboBox.getSelectionModel().getSelectedItem());
        contract.setConclusionDate(conclusionDate);


        if (AdminPanelContext.getContractID() == -1) {
            contractController.saveContract(contract);
            AlertHelper.showSimpleAlertDialog("Success", "New contract was created!", Alert.AlertType.INFORMATION);
        } else {
            contractController.editContract(contract);
            AlertHelper.showSimpleAlertDialog("Success", "Contract was updated!", Alert.AlertType.INFORMATION);
        }
        AdminPanelContext.setContractID(-1);
        ((Stage) saveButton.getScene().getWindow()).close();
    }

    public void onCancel(ActionEvent actionEvent) {
        AdminPanelContext.setContractID(-1);
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}
