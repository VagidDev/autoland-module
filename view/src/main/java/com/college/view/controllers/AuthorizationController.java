package com.college.view.controllers;

import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
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

    private com.college.controller.AuthorizationController authorizationController;

    @FXML
    private void initialize() {
        this.authorizationController = ControllerManager.getAuthorizationController();
    }

    public void openRegisterForm(ActionEvent event) {
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Account Information", "account-register-form.fxml");
    }


    public void onLoginButtonAction(ActionEvent event) {
        boolean isAuthorized = authorizationController.login(loginTextField.getText(), passwordTextField.getText());

        if (!isAuthorized) {
            AlertHelper.showInvalidLoginAlert();
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
