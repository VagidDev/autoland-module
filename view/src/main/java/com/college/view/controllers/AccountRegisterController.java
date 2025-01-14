package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AccountRegisterController {
    @FXML
    private Button confirmButton;

    @FXML
    public void confirmButtonClicked(ActionEvent event) throws IOException {
        StageService.closeCurrentStage(event);
        StageService.buildSimpleStage("Registration", "registration-form.fxml");
    }
}
