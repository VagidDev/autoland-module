package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class AccountRegisterController {
    @FXML
    private Button confirmButton;

    public void confirmButtonClicked(ActionEvent event) {
        //TODO: should be another option, that will be activated if this form was called from AccountController
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Registration", "registration-form.fxml");
    }

    public void cancelButtonClicked(ActionEvent event) {
        StageService.closeStageAndOpenPrevious();
    }
}
