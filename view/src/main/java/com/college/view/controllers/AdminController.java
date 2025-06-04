package com.college.view.controllers;

import com.college.controller.AutomobileController;
import com.college.controller.UserController;
import com.college.controller.DealerController;
import com.college.controller.WarrantyController;
import com.college.model.entity.Automobile;
import com.college.model.entity.Dealer;
import com.college.model.entity.User;
import com.college.model.entity.Warranty;
import com.college.view.core.ControllerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    private UserController userController;
    private DealerController dealerController;
    private WarrantyController warrantyController;
    private AutomobileController automobileController;

    public void initialize() {
        //initialize objects
        userController = ControllerManager.getUserController();
        dealerController = ControllerManager.getDealerController();
        warrantyController = ControllerManager.getWarrantyController();
        automobileController = ControllerManager.getAutomobileController();

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
        }
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

        TableColumn<Warranty, String> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDuration())));

        TableColumn<Warranty, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));

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

        TableColumn<Automobile, String> placeCountColumn = new TableColumn<>("Place count");
        placeCountColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPlaceCount())));

        TableColumn<Automobile, String> prodYearColumn = new TableColumn<>("Production Year");
        prodYearColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProdYear())));

        this.automobileTableView.getColumns().addAll(idColumn, markColumn, modelColumn, bodyTypeColumn, placeCountColumn, prodYearColumn);
        ObservableList<Automobile> automobileList = FXCollections.observableArrayList(automobiles);
        this.automobileTableView.setItems(automobileList);
        this.automobileTableView.refresh();
    }
}
