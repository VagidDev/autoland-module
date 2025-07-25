package com.college.view.controllers;

import com.college.controller.*;
import com.college.controller.DealerController;
import com.college.controller.WarrantyController;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.*;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class AdminController {
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableView<Dealer> dealerTableView;
    @FXML
    private TableView<Warranty> warrantyTableView;
    @FXML
    private TableView<Automobile> automobileTableView;
    @FXML
    private TableView<Equipment> equipmentTableView;
    @FXML
    private TableView<Contract> contractTableView;

    private UserController userController;
    private DealerController dealerController;
    private WarrantyController warrantyController;
    private AutomobileController automobileController;
    private EquipmentController equipmentController;
    private ContractController contractController;

    public void initialize() {
        //initialize objects
        userController = ControllerManager.getUserController();
        dealerController = ControllerManager.getDealerController();
        warrantyController = ControllerManager.getWarrantyController();
        automobileController = ControllerManager.getAutomobileController();
        equipmentController = ControllerManager.getEquipmentController();
        contractController = ControllerManager.getContractController();

        //TODO: add logic for loading data from database and inserting it into tableView
        loadUsers(userController.getUsers());


        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            onSelectedTabChange(newValue);
        });

    }

    @FXML
    public void onSelectedTabChange(Tab selectedTab) {
        switch (selectedTab.getText().toLowerCase()) {
            case "users" -> loadUsers(userController.getUsers());
            case "dealers" -> loadDealers(dealerController.getAllDealers());
            case "warranties" -> loadWarranties(warrantyController.getAllWarranty());
            case "automobiles" -> loadAutomobiles(automobileController.getAllAutomobiles());
            case "equipments" -> loadEquipments(equipmentController.getAllEquipments());
            case "contracts" -> loadContracts(contractController.getAllContracts());
        }
    }

    public void exitAction(ActionEvent actionEvent) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }

    private void loadUsers(List<User> users) {
        this.userTableView.getColumns().clear();

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> loginColumn = new TableColumn<>("Login");
        loginColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<User, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));

        TableColumn<User, String> birthdayColumn = new TableColumn<>("Birthday");
        birthdayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBirthday().toString()));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        TableColumn<User, String> telephoneColumn = new TableColumn<>("Telephone");
        telephoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));

        TableColumn<User, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));

        TableColumn<User, String> avatarColumn = new TableColumn<>("Avatar");
        avatarColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvatar()));

        this.userTableView.getColumns().addAll(idColumn, loginColumn, passwordColumn, nameColumn, surnameColumn, birthdayColumn, emailColumn, telephoneColumn, addressColumn, avatarColumn);
        ObservableList<User> userList = FXCollections.observableArrayList(users);
        this.userTableView.setItems(userList);
        this.userTableView.refresh();
    }

    private void loadDealers(List<Dealer> dealers) {
        this.dealerTableView.getColumns().clear();

        TableColumn<Dealer, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Dealer, String> nameColumn = new TableColumn<>("Dealer Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Dealer, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));

        TableColumn<Dealer, String> mobilePhoneNumberColumn = new TableColumn<>("Mobile Phone Number");
        mobilePhoneNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelephone()));

        TableColumn<Dealer, String> faxNumberColumn = new TableColumn<>("FAX Number");
        faxNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFax()));

        this.dealerTableView.getColumns().addAll(idColumn, nameColumn, addressColumn, mobilePhoneNumberColumn, faxNumberColumn);
        ObservableList<Dealer> dealerList = FXCollections.observableArrayList(dealers);
        this.dealerTableView.setItems(dealerList);
        this.dealerTableView.refresh();
    }

    private void loadWarranties(List<Warranty> warranties) {
        this.warrantyTableView.getColumns().clear();

        TableColumn<Warranty, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Warranty, String> nameColumn = new TableColumn<>("Warranty Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Warranty, Number> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDuration()));

        TableColumn<Warranty, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()));

        this.warrantyTableView.getColumns().addAll(idColumn, nameColumn, durationColumn, priceColumn);
        ObservableList<Warranty> warrantyList = FXCollections.observableArrayList(warranties);
        this.warrantyTableView.setItems(warrantyList);
        this.warrantyTableView.refresh();
    }

    private void loadAutomobiles(List<Automobile> automobiles) {
        this.automobileTableView.getColumns().clear();

        TableColumn<Automobile, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Automobile, String> markColumn = new TableColumn<>("Mark");
        markColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMark()));

        TableColumn<Automobile, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));

        TableColumn<Automobile, String> bodyTypeColumn = new TableColumn<>("Body Type");
        bodyTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBodyType().getName()));

        TableColumn<Automobile, Number> placeCountColumn = new TableColumn<>("Place count");
        placeCountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPlaceCount()));

        TableColumn<Automobile, Number> prodYearColumn = new TableColumn<>("Production Year");
        prodYearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getProdYear()));

        this.automobileTableView.getColumns().addAll(idColumn, markColumn, modelColumn, bodyTypeColumn, placeCountColumn, prodYearColumn);
        ObservableList<Automobile> automobileList = FXCollections.observableArrayList(automobiles);
        this.automobileTableView.setItems(automobileList);
        this.automobileTableView.refresh();
    }

    private void loadEquipments(List<Equipment> equipments) {
        this.equipmentTableView.getColumns().clear();

        TableColumn<Equipment, String> automobileColumn = new TableColumn<>("Automobile");
        automobileColumn.setCellValueFactory( cellData -> new SimpleStringProperty(cellData.getValue().getAutomobile().getMark() + " " + cellData.getValue().getAutomobile().getModel()));

        TableColumn<Equipment, Number> idColumn = new TableColumn<>("Equipment ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId().getEquipmentId()));

        TableColumn<Equipment, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Equipment, String> engineTypeColumn = new TableColumn<>("Engine");
        engineTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEngineType().getName()));

        TableColumn<Equipment, Number> horsepowerColumn = new TableColumn<>("Horsepower");
        horsepowerColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHorsepower()));

        TableColumn<Equipment, String> suspensionColumn = new TableColumn<>("Suspension");
        suspensionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSuspensionType().getName()));

        TableColumn<Equipment, String> driveColumn = new TableColumn<>("Drive");
        driveColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDriveType().getName()));

        TableColumn<Equipment, String> gearboxColumn = new TableColumn<>("Gearbox");
        gearboxColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGearboxType().getName()));

        TableColumn<Equipment, String> fuelColumn = new TableColumn<>("Fuel");
        fuelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFuelType().getName()));

        TableColumn<Equipment, String> bodyKitColumn = new TableColumn<>("Body Kit");
        bodyKitColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBodyKit()));

        TableColumn<Equipment, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()));


        this.equipmentTableView.getColumns().addAll(automobileColumn, idColumn, nameColumn, engineTypeColumn, horsepowerColumn, suspensionColumn, driveColumn, gearboxColumn, fuelColumn, bodyKitColumn, priceColumn);
        ObservableList<Equipment> equipmentList = FXCollections.observableArrayList(equipments);
        this.equipmentTableView.setItems(equipmentList);
        this.equipmentTableView.refresh();
    }

    private void loadContracts(List<Contract> contracts) {
        this.contractTableView.getColumns().clear();

        TableColumn<Contract, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Contract, String> userColumn = new TableColumn<>("User");
        userColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUser().getName() + " " + cellData.getValue().getUser().getSurname()));

        TableColumn<Contract, String> dealerColumn = new TableColumn<>("Dealer");
        dealerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDealer().getName()));

        TableColumn<Contract, String> automobileColumn = new TableColumn<>("Automobile");
        automobileColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEquipment().getAutomobile().getMark() + " " + cellData.getValue().getEquipment().getAutomobile().getModel()));

        TableColumn<Contract, String> equipmentColumn = new TableColumn<>("Equipment");
        equipmentColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEquipment().getName()));

        TableColumn<Contract, String> warrantyColumn = new TableColumn<>("Warranty");
        warrantyColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getWarranty().getName()));

        TableColumn<Contract, String> dateColumn = new TableColumn<>("Data");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConclusionDate().toString()));

        TableColumn<Contract, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> {
            var contract = cellData.getValue();
            double price = contract.getEquipment().getPrice() + contract.getWarranty().getPrice();
            return new SimpleDoubleProperty(price);
        });

        this.contractTableView.getColumns().addAll(idColumn, userColumn, dealerColumn, automobileColumn, equipmentColumn, warrantyColumn, dateColumn, priceColumn);
        ObservableList<Contract> contractList = FXCollections.observableArrayList(contracts);
        this.contractTableView.setItems(contractList);
        this.contractTableView.refresh();
    }

    public void addUserAction(ActionEvent actionEvent) {
        try {
            SceneRouterService.getSceneRouter().showDialogForm("add-update-user-form.fxml", "Add User");
            loadUsers(userController.getUsers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserAction(ActionEvent actionEvent) {
        try {
            if (userTableView.getSelectionModel().getSelectedItem() != null) {
                AdminPanelContext.setUserID(userTableView.getSelectionModel().getSelectedItem().getId());
                SceneRouterService.getSceneRouter().showDialogForm("add-update-user-form.fxml", "Update User");
                loadUsers(userController.getUsers());
            } else {
                AlertHelper.showSimpleAlertDialog("Warning", "Please select user for editing", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserAction(ActionEvent actionEvent) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            boolean confirmDeleting = AlertHelper.showConfirmationDialog("Do you really want to delete user with login '" + selectedUser.getLogin() +"'?");
            if (confirmDeleting) {
                try {
                    userController.deleteUser(selectedUser);
                    AlertHelper.showSimpleAlertDialog("Success", "User deleted!", Alert.AlertType.INFORMATION);
                    loadUsers(userController.getUsers());
                } catch (CascadeDependencyException e) {
                    AlertHelper.showSimpleAlertDialog("Error", "You cannot delete this user, he is used in contracts!", Alert.AlertType.ERROR);
                }
            }
        } else {
            AlertHelper.showSimpleAlertDialog("Warning", "Please select user for deleting", Alert.AlertType.WARNING);
        }
    }

    public void addDealerAction(ActionEvent actionEvent) {
        try {
            SceneRouterService.getSceneRouter().showDialogForm("add-update-dealer-form.fxml", "Add Dealer");
            loadDealers(dealerController.getAllDealers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDealerAction(ActionEvent actionEvent) {
        try {
            if (dealerTableView.getSelectionModel().getSelectedItem() != null) {
                AdminPanelContext.setDealerID(dealerTableView.getSelectionModel().getSelectedItem().getId());
                SceneRouterService.getSceneRouter().showDialogForm("add-update-dealer-form.fxml", "Update Dealer");
                loadDealers(dealerController.getAllDealers());
            } else {
                AlertHelper.showSimpleAlertDialog("Warning", "Please select dealer for editing", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDealerAction(ActionEvent actionEvent) {
        Dealer selectedDealer = dealerTableView.getSelectionModel().getSelectedItem();
        if (selectedDealer != null) {
            boolean confirmDeleting = AlertHelper.showConfirmationDialog("Do you really want to delete dealer with name '" + selectedDealer.getName() +"'?");
            if (confirmDeleting) {
                try {
                    dealerController.deleteDealer(selectedDealer);
                    AlertHelper.showSimpleAlertDialog("Success", "Dealer deleted!", Alert.AlertType.INFORMATION);
                    loadDealers(dealerController.getAllDealers());
                } catch (CascadeDependencyException e) {
                    AlertHelper.showSimpleAlertDialog("Error", "You cannot delete this dealer, it is used in contracts!", Alert.AlertType.ERROR);
                }
            }
        } else {
            AlertHelper.showSimpleAlertDialog("Warning", "Please select dealer for deleting", Alert.AlertType.WARNING);
        }
    }

    public void addWarrantyAction(ActionEvent actionEvent) {
        try {
            SceneRouterService.getSceneRouter().showDialogForm("add-update-warranty-form.fxml", "Add Warranty");
            loadWarranties(warrantyController.getAllWarranty());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWarrantyAction(ActionEvent actionEvent) {
        try {
            if (warrantyTableView.getSelectionModel().getSelectedItem() != null) {
                AdminPanelContext.setWarrantyID(warrantyTableView.getSelectionModel().getSelectedItem().getId());
                SceneRouterService.getSceneRouter().showDialogForm("add-update-warranty-form.fxml", "Update Warranty");
                loadWarranties(warrantyController.getAllWarranty());
            } else {
                AlertHelper.showSimpleAlertDialog("Warning", "Please select warranty for editing", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWarrantyAction(ActionEvent actionEvent) {
        Warranty selectedWarranty = warrantyTableView.getSelectionModel().getSelectedItem();
        if (selectedWarranty != null) {
            boolean confirmDeleting = AlertHelper.showConfirmationDialog("Do you really want to delete warranty with name '" + selectedWarranty.getName() +"'?");
            if (confirmDeleting) {
                try {
                    warrantyController.deleteWarranty(selectedWarranty);
                    AlertHelper.showSimpleAlertDialog("Success", "Warranty deleted!", Alert.AlertType.INFORMATION);
                    loadWarranties(warrantyController.getAllWarranty());
                } catch (CascadeDependencyException e) {
                    AlertHelper.showSimpleAlertDialog("Error", "You cannot delete this warranty, it is used in contracts!", Alert.AlertType.ERROR);
                }
            }
        } else {
            AlertHelper.showSimpleAlertDialog("Warning", "Please select warranty for deleting", Alert.AlertType.WARNING);
        }
    }

    public void addAutomobileAction(ActionEvent actionEvent) {
        try {
            SceneRouterService.getSceneRouter().showDialogForm("add-update-automobile-form.fxml", "Add Automobile");
            loadAutomobiles(automobileController.getAllAutomobiles());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAutomobileAction(ActionEvent actionEvent) {
        try {
            if (automobileTableView.getSelectionModel().getSelectedItem() != null) {
                AdminPanelContext.setAutomobileID(automobileTableView.getSelectionModel().getSelectedItem().getId());
                SceneRouterService.getSceneRouter().showDialogForm("add-update-automobile-form.fxml", "Update Automobile");
                loadAutomobiles(automobileController.getAllAutomobiles());
            } else {
                AlertHelper.showSimpleAlertDialog("Warning", "Please select automobile for editing", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAutomobileAction(ActionEvent actionEvent) {
        Automobile selectedAutomobile = automobileTableView.getSelectionModel().getSelectedItem();
        if (selectedAutomobile != null) {
            boolean confirmDeleting = AlertHelper.showConfirmationDialog("Do you really want to delete automobile with name '" + selectedAutomobile.getMark()
                    + " " + selectedAutomobile.getModel() + "'?");
            if (confirmDeleting) {
                try {
                    automobileController.deleteAutomobile(selectedAutomobile);
                    AlertHelper.showSimpleAlertDialog("Success", "Automobile deleted!", Alert.AlertType.INFORMATION);
                    loadAutomobiles(automobileController.getAllAutomobiles());
                } catch (CascadeDependencyException e) {
                    AlertHelper.showSimpleAlertDialog("Error", "You cannot delete this automobile, it is used in contracts!", Alert.AlertType.ERROR);
                }
            }
        } else {
            AlertHelper.showSimpleAlertDialog("Warning", "Please select automobile for deleting", Alert.AlertType.WARNING);
        }
    }

    public void addEquipmentAction(ActionEvent actionEvent) {
        try {
            SceneRouterService.getSceneRouter().showDialogForm("add-update-equipment-form.fxml", "Add Equipment");
            loadEquipments(equipmentController.getAllEquipments());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEquipmentAction(ActionEvent actionEvent) {
        try {
            if (equipmentTableView.getSelectionModel().getSelectedItem() != null) {
                AdminPanelContext.setEquipmentID(equipmentTableView.getSelectionModel().getSelectedItem().getId());
                SceneRouterService.getSceneRouter().showDialogForm("add-update-equipment-form.fxml", "Update Equipment");
                loadEquipments(equipmentController.getAllEquipments());
            } else {
                AlertHelper.showSimpleAlertDialog("Warning", "Please select equipment for editing", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEquipmentAction(ActionEvent actionEvent) {
        Equipment selectedEquipment = equipmentTableView.getSelectionModel().getSelectedItem();
        if (selectedEquipment != null) {
            boolean confirmDeleting = AlertHelper.showConfirmationDialog("Do you really want to delete equipment with name '" + selectedEquipment.getName()
                    + " for " + selectedEquipment.getAutomobile().getMark() + " " + selectedEquipment.getAutomobile().getModel() + "'?");
            if (confirmDeleting) {
                try {
                    equipmentController.deleteEquipment(selectedEquipment);
                    AlertHelper.showSimpleAlertDialog("Success", "Equipment deleted!", Alert.AlertType.INFORMATION);
                    loadEquipments(equipmentController.getAllEquipments());
                } catch (CascadeDependencyException e) {
                    AlertHelper.showSimpleAlertDialog("Error", "You cannot delete this equipment, it is used in contracts!", Alert.AlertType.ERROR);
                }
            }
        } else {
            AlertHelper.showSimpleAlertDialog("Warning", "Please select equipment for deleting", Alert.AlertType.WARNING);
        }
    }

    public void addContractAction(ActionEvent actionEvent) {
        try {
            SceneRouterService.getSceneRouter().showDialogForm("add-update-contract-form.fxml", "Add Contract");
            loadContracts(contractController.getAllContracts());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateContractAction(ActionEvent actionEvent) {
        try {
            if (contractTableView.getSelectionModel().getSelectedItem() != null) {
                AdminPanelContext.setContractID(contractTableView.getSelectionModel().getSelectedItem().getId());
                SceneRouterService.getSceneRouter().showDialogForm("add-update-contract-form.fxml", "Update Contract");
                loadContracts(contractController.getAllContracts());
            } else {
                AlertHelper.showSimpleAlertDialog("Warning", "Please select contract for editing", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContractAction(ActionEvent actionEvent) {
        Contract selectedContract = contractTableView.getSelectionModel().getSelectedItem();
        if (selectedContract != null) {
            boolean confirmDeleting = AlertHelper.showConfirmationDialog("Do you really want to delete contract for user '"
                    + selectedContract.getUser().getName() + " " + selectedContract.getUser().getSurname() + "'?");
            if (confirmDeleting) {
                try {
                    contractController.deleteContract(selectedContract);
                    AlertHelper.showSimpleAlertDialog("Success", "Contract deleted!", Alert.AlertType.INFORMATION);
                    loadContracts(contractController.getAllContracts());
                    //should not be thrown
                } catch (CascadeDependencyException e) {
                    AlertHelper.showSimpleAlertDialog("Error", "You cannot delete this contract!", Alert.AlertType.ERROR);
                }
            }
        } else {
            AlertHelper.showSimpleAlertDialog("Warning", "Please select contract for deleting", Alert.AlertType.WARNING);
        }
    }

}
