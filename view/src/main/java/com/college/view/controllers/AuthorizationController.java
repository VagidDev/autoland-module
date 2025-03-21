package com.college.view.controllers;

import com.college.view.core.ControllerManager;
import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private void showInvalidLoginAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Authorization Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect login or password. Please try again.");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public void openRegisterForm(ActionEvent event) {
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Account Information", "account-register-form.fxml");
    }


    public void onLoginButtonAction(ActionEvent event) {
        boolean isAuthorized = authorizationController.login(loginTextField.getText(), passwordTextField.getText());

        if (!isAuthorized) {
            showInvalidLoginAlert();
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
