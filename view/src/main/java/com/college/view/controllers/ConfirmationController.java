package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ConfirmationController {
    @FXML
    private Button confirmationButton;

    public void onConfirmButtonClicked(ActionEvent actionEvent) {
        StageService.closeStageAndClearStack();
        StageService.buildAndShowStage("Home", "home-form.fxml");
    }
}
