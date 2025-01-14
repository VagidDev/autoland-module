package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationController {
    @FXML
    private Button registerButton;

    @FXML
    public void openRegisterForm(ActionEvent event) throws IOException {
        StageService.closeCurrentStage(event);
        StageService.showNewStage("Register form", "account-register-form.fxml");
    }
}
