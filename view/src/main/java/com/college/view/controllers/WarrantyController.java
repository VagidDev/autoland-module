package com.college.view.controllers;

import com.college.model.entity.Warranty;
import com.college.view.core.ContractBuilder;
import com.college.view.core.ControllerManager;
import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.List;

public class WarrantyController {
    @FXML
    private FlowPane flowPane;

    private com.college.controller.WarrantyController warrantyController;
    private int selectedWarrantyId;

    public void initialize() {
        warrantyController = ControllerManager.getWarrantyController();
        List<Warranty> warranties = warrantyController.getAllWarranty();

        warranties.forEach(warranty -> {
            flowPane.getChildren().add(createWarrantyPane(warranty.getId(), warranty.getName(), warranty.getDuration(), warranty.getPrice()));
        });

    }

    public Pane createWarrantyPane(int id, String warrantyName, int duration, double price) {
        Pane pane = new Pane();
        pane.setPrefSize(350, 200);
        pane.getStyleClass().add("warranty-pane");
        pane.setId(String.valueOf(id));

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
            this.selectedWarrantyId = Integer.parseInt(clickedPane.getId());
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
        ContractBuilder.setWarrantyById(selectedWarrantyId);
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Dealer", "dealer-form.fxml");
    }
}
