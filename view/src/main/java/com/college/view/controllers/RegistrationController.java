package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private Button submitButton;


    public void submitButtonClicked(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if (password.equals(confirmPassword) && !password.isEmpty()) {
            FXMLLoader loader = StageService.loadFXML("authorization-form.fxml");
            AuthorizationController controller = loader.getController();
            controller.setLoginAndPasswordFields(login, password);

            StageService.closeStageAndClearStack();
            StageService.buildStage("Authorization", loader).show();
        }
    }

    public void cancelButtonClicked(ActionEvent event) {
        StageService.closeStageAndOpenPrevious();
    }
}
