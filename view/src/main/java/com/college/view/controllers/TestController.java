package com.college.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class TestController {
    @FXML
    private Button registerButton;

    @FXML
    public void openRegisterForm() throws IOException {
        //new AccountRegisterForm(new Stage()).show();
    }
}
