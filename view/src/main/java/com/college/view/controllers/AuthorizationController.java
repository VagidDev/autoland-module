package com.college.view.controllers;

import com.college.view.core.*;
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
        SceneRouterService.getSceneRouter().switchTo("account-register-form.fxml", AnimationType.FADE);
    }


    public void onLoginButtonAction(ActionEvent event) {
        boolean isAuthorized = authorizationController.login(loginTextField.getText(), passwordTextField.getText());

        if (!isAuthorized) {
            AlertHelper.showInvalidLoginAlert();
            return;
        }
        SceneRouterService.getSceneRouter().switchTo("home-form.fxml", AnimationType.ZOOM);
    }

    public void setLoginAndPasswordFields(String login, String password) {
        loginTextField.setText(login);
        passwordTextField.setText(password);
    }
}
