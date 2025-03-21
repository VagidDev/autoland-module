package com.college.view.controllers;

import com.college.controller.EquipmentController;
import com.college.model.entity.Equipment;
import com.college.view.core.ControllerManager;
import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ShopController {
    @FXML
    private FlowPane flowPane;
    private EquipmentController equipmentController;

    public void initialize() {
        this.equipmentController = ControllerManager.getEquipmentController();
        List<Equipment> equipments = equipmentController.getAllEquipments();

        equipments.forEach(equipment -> {
            flowPane.getChildren().add(createCarButton(equipment.getAutomobile().getMark() + " " + equipment.getName(),
                    equipment.getPrice(), equipment.getImagePath()));
        });
    }

    public Pane createCarButton(String mark, double price, String imagePath) {
        Pane pane = new Pane();
        pane.setPrefSize(270, 220);
        pane.getStyleClass().add("automobile-pane");

        var path = Path.of(imagePath).toAbsolutePath();

        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(Files.newInputStream(path)));
            imageView.setFitWidth(220);
            imageView.setFitHeight(124);
            imageView.setLayoutX(25);
            imageView.setLayoutY(14);
            imageView.setPickOnBounds(true);
        } catch (IOException e) {
            //TODO: create case, that will exclude NullPointerException
            throw new RuntimeException(e);
        }


        Label labelMark = new Label(mark);
        labelMark.setLayoutX(25);
        labelMark.setLayoutY(146);
        labelMark.setTextFill(Color.web("#a4a4a4"));
        labelMark.setFont(Font.font("Lucida Bright Demibold", 16));


        Label labelPrice = new Label(price + " $");
        labelPrice.setLayoutX(25);
        labelPrice.setLayoutY(175);
        labelPrice.setTextFill(Color.web("#a4a4a4"));
        labelPrice.setFont(Font.font("Lucida Bright Demibold", 16));

        pane.getChildren().addAll(imageView, labelMark, labelPrice);

        pane.setOnMouseClicked(event -> {
            StageService.closeAndSaveStage();
            StageService.buildAndShowStage("Car", "auto-form.fxml");
        });

        return pane;
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        StageService.closeStageAndOpenPrevious();
    }

}
