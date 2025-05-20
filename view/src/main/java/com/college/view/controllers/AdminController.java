package com.college.view.controllers;

import com.college.model.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class AdminController {
    @FXML
    private TableView<User> userTableView;

    public void initialize() {
        //TODO: add logic for loading data from database and inserting it into tableView
    }

}
