package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class AccountController {
    @FXML
    private ImageView avatar;

    public void initialize() {
        Circle circle = new Circle(100, 100, 100);
        avatar.setClip(circle);

        Platform.runLater(() -> {
            Stage stage = (Stage) avatar.getScene().getWindow();
            stage.setOnCloseRequest(event -> StageService.buildAndShowStage("Home", "home-form.fxml"));
        });

    }

    public void editButtonClicked(ActionEvent actionEvent) {
        Stage currentStage = StageService.getCurrentStage();
        FXMLLoader loader = StageService.loadFXML("account-register-form.fxml");
        Stage tmpStage = StageService.buildStage("Edit Account", loader);
        currentStage.hide();
        tmpStage.showAndWait();
        StageService.unregisterStage(tmpStage);
        currentStage.show();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        StageService.closeStageAndOpenPrevious();
    }
}
