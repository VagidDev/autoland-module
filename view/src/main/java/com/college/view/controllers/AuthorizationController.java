package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AuthorizationController {
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;


    public void openRegisterForm(ActionEvent event) {
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Account Information", "account-register-form.fxml");
    }


    public void onLoginButtonAction(ActionEvent event) {
        if (!loginTextField.getText().equals("vaxa") || !passwordTextField.getText().equals("qwerty")) {
            return;
        }
        StageService.closeStage();
        StageService.buildAndShowStage("Home", "home-form.fxml");
    }

    public void setLoginAndPasswordFields(String login, String password) {
        loginTextField.setText(login);
        passwordTextField.setText(password);
    }
}
