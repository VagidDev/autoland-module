package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AuthorizationController {
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    public void openRegisterForm(ActionEvent event) throws IOException {
        StageService.closeCurrentStage(event);
        StageService.buildSimpleStage("Account Information", "account-register-form.fxml");
    }

    @FXML
    public void onLoginButtonAction(ActionEvent event) {
        //just for checking
        if (!loginTextField.getText().equals("vaxa") || !passwordTextField.getText().equals("qwerty")) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("You are logged in");
        alert.setContentText("Welcome " + loginTextField.getText());
        alert.showAndWait();
    }

    public void setLoginAndPasswordFields(String login, String password) {
        loginTextField.setText(login);
        passwordTextField.setText(password);
    }
}
