package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;

public class WarrantyController {
    @FXML
    private FlowPane flowPane;

    public void initialize() {
        flowPane.getChildren().add(createWarrantyPane("Simple", 24, 5000));
        flowPane.getChildren().add(createWarrantyPane("Simple", 24, 5000));
        flowPane.getChildren().add(createWarrantyPane("Simple", 24, 5000));
        flowPane.getChildren().add(createWarrantyPane("Simple", 24, 5000));
    }

    public Pane createWarrantyPane(String warrantyName, int duration, double price) {
        Pane pane = new Pane();
        pane.setPrefSize(350, 200);
        pane.getStyleClass().add("warranty-pane");

        Label warrantyLabel = new Label(warrantyName);
        warrantyLabel.setLayoutX(25);
        warrantyLabel.setLayoutY(30);
        warrantyLabel.setFont(Font.font("Lucida Bright Demibold", 18));

        Label durationLabel = new Label("Duration: " + duration);
        durationLabel.setLayoutX(25);
        durationLabel.setLayoutY(85);
        durationLabel.setFont(Font.font("Lucida Bright Demibold", 18));

        Label priceLabel = new Label("Price " + price + '$');
        priceLabel.setLayoutX(25);
        priceLabel.setLayoutY(140);
        priceLabel.setFont(Font.font("Lucida Bright Demibold", 18));

        pane.getChildren().addAll(warrantyLabel, durationLabel, priceLabel);

        pane.setOnMouseClicked(this::clickWarrantyPane);

        return pane;
    }

    private void clickWarrantyPane(MouseEvent event) {
        Object clickedObject = event.getSource();
        if (clickedObject instanceof Pane clickedPane) {
            flowPane.getChildren().stream()
                    .filter(warrantyPane -> warrantyPane.getStyleClass().contains("clicked-warranty-pane"))
                    .forEach(warrantyPane -> warrantyPane.getStyleClass().remove("clicked-warranty-pane"));
            clickedPane.getStyleClass().add("clicked-warranty-pane");
        }
    }

    public void clickCancelButton(ActionEvent actionEvent) {
        StageService.closeStageAndOpenPrevious();
    }

    public void clickBuyButton(ActionEvent actionEvent) {
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Dealer", "dealer-form.fxml");
    }
}
