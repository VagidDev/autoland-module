package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountController {
    @FXML
    private ImageView avatar;

    public void initialize() {
        Circle circle = new Circle(100, 100, 100);
        avatar.setClip(circle);

        Platform.runLater(() -> {
            Stage stage = (Stage) avatar.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                try {
                    StageService.buildAndShowStage("Home", "home-form.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });

    }

    public synchronized void editButtonClicked(ActionEvent actionEvent) throws IOException {
        Stage currentStage = StageService.getCurrentStageByEvent(actionEvent);
        FXMLLoader loader = StageService.loadFXML("account-register-form.fxml");
        currentStage.hide();
        StageService.buildStage("Edit Account", loader).showAndWait();
        currentStage.show();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        try {
            StageService.buildAndShowStage("Home", "home-form.fxml");
            StageService.closeCurrentStage(actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
