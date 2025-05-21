package com.college.view.controllers;

import com.college.controller.UserController;
import com.college.model.entity.Contract;
import com.college.model.entity.User;
import com.college.view.core.ControllerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AdminController {
    @FXML
    private TableView<User> userTableView;

    private UserController userController;

    public void initialize() {
        //initialize objects
        userController = ControllerManager.getUserController();

        //TODO: add logic for loading data from database and inserting it into tableView
        loadUsers(userController.getUsers());
    }


    private void loadUsers(List<User> users) {
        this.userTableView.getColumns().clear();

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> loginColumn = new TableColumn<>("Login");
        loginColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getLogin()));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPassword()));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<User, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSurname()));

        TableColumn<User, String> birthdayColumn = new TableColumn<>("Birthday");
        birthdayColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBirthday().toString()));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));

        TableColumn<User, String> telephoneColumn = new TableColumn<>("Telephone");
        telephoneColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTelephone()));

        TableColumn<User, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAddress()));

        TableColumn<User, String> avatarColumn = new TableColumn<>("Avatar");
        avatarColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAvatar()));

        this.userTableView.getColumns().addAll(idColumn, loginColumn, passwordColumn, nameColumn, surnameColumn, birthdayColumn, emailColumn, telephoneColumn, addressColumn, avatarColumn);
        ObservableList<User> userList = FXCollections.observableArrayList(users);
        this.userTableView.setItems(userList);
        this.userTableView.refresh();
    }

}
