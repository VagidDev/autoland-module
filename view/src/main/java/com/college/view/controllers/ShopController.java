package com.college.view.controllers;

import com.college.controller.EquipmentController;
import com.college.model.entity.Equipment;
import com.college.model.entity.keys.EquipmentId;
import com.college.view.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class ShopController {
    @FXML
    private FlowPane flowPane;
    @FXML
    private VBox bodyBox;
    @FXML
    private VBox engineBox;
    @FXML
    private VBox gearboxBox;
    @FXML
    private ToggleButton toggleAscending;
    @FXML
    private ToggleButton toggleDescending;
    @FXML
    private ToggleButton toggleAlphabetic;
    @FXML
    private TextField searchBox;

    private ToggleGroup toggleGroup;
    private EquipmentController equipmentController;

    public void initialize() {
        toggleGroup = new ToggleGroup();
        toggleAscending.setToggleGroup(toggleGroup);
        toggleDescending.setToggleGroup(toggleGroup);
        toggleAlphabetic.setToggleGroup(toggleGroup);

        this.equipmentController = ControllerManager.getEquipmentController();
        List<Equipment> equipments = equipmentController.getAllEquipments();

        loadEquipments(equipments);
    }

    private void loadEquipments(List<Equipment> equipments) {
        equipments.forEach(equipment -> {
            flowPane.getChildren().add(createCarButton(equipment.getAutomobile().getId(),
                    equipment.getAutomobile().getMark() + " " + equipment.getAutomobile().getModel(),
                    equipment.getPrice(), equipment.getImagePath()));
        });
    }

    public Pane createCarButton(int autoId, String mark, double price, String imagePath) {
        Pane pane = new Pane();
        pane.setPrefSize(340, 270);
        pane.getStyleClass().add("automobile-pane");

        pane.setId(String.valueOf(autoId));


        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(imagePath));
        } catch (Exception e) {
            //TODO: create case, that will exclude NullPointerException
            try {
                var path = Path.of("view/src/main/resources/images/test.jpg").toAbsolutePath();
                imageView = new ImageView(new Image(Files.newInputStream(path)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        imageView.setFitWidth(290);
        imageView.setFitHeight(163);
        imageView.setLayoutX(25);
        imageView.setLayoutY(25);
        imageView.setPickOnBounds(true);
        imageView.setCache(true);
        imageView.setSmooth(true);


        Label labelMark = new Label(mark);
        labelMark.setLayoutX(25);
        labelMark.setLayoutY(199);//29
        labelMark.setTextFill(Color.web("#a4a4a4"));
        labelMark.setFont(Font.font("Lucida Bright Demibold", 16));


        Label labelPrice = new Label(price + " $");
        labelPrice.setLayoutX(25);
        labelPrice.setLayoutY(228);
        labelPrice.setTextFill(Color.web("#a4a4a4"));
        labelPrice.setFont(Font.font("Lucida Bright Demibold", 16));

        pane.getChildren().addAll(imageView, labelMark, labelPrice);

        pane.setOnMouseClicked(event -> {
            if (event.getSource() instanceof Pane clickedPane) {
                ContractBuilder.setAutomobileById(Integer.parseInt(clickedPane.getId()));
                SceneRouterService.getSceneRouter().switchTo("auto-form.fxml", AnimationType.ZOOM);
            } else {
                System.err.println("Automobile was not wrote!");
            }
        });
        return pane;
    }

    public void onSortToggleButton(ActionEvent actionEvent) {
        flowPane.getChildren().clear();
        ToggleButton toggleButton = (ToggleButton) actionEvent.getSource();
        List<Equipment> equipments = switch (toggleButton.getText()) {
            case "Ascending" -> equipmentController.getAllEquipments().stream()
                    .sorted(Comparator.comparingDouble(Equipment::getPrice))
                    .toList();
            case "Descending" -> equipmentController.getAllEquipments().stream()
                    .sorted(Comparator.comparingDouble(Equipment::getPrice).reversed())
                    .toList();
            case "Alphabetic" -> equipmentController.getAllEquipments().stream()
                    .sorted(Comparator.comparing(equipment -> equipment.getAutomobile().getMark() + " " + equipment.getName()))
                    .toList();
            default -> equipmentController.getAllEquipments();
        };
        loadEquipments(equipments);
    }

    public void onCheckBoxChanged(ActionEvent actionEvent) {
        Toggle toggle = toggleGroup.getSelectedToggle();
        if (toggle != null) {
            toggle.setSelected(false);
        }

        flowPane.getChildren().clear();

        List<String> bodyTypes = bodyBox.getChildren().stream()
                .filter(item -> item instanceof CheckBox)
                .map(item -> (CheckBox) item)
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .map(String::toLowerCase)
                .toList();

        List<String> engineTypes = engineBox.getChildren().stream()
                .filter(item -> item instanceof CheckBox)
                .map(item -> (CheckBox) item)
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .map(String::toLowerCase)
                .toList();

        List<String> gearboxTypes = gearboxBox.getChildren().stream()
                .filter(item -> item instanceof CheckBox)
                .map(item -> (CheckBox) item)
                .filter(CheckBox::isSelected).map(CheckBox::getText)
                .map(String::toLowerCase)
                .toList();

        List<Equipment> equipments = equipmentController.getAllEquipments().stream()
                .filter(equipment -> bodyTypes.contains(equipment.getAutomobile().getBodyType().getName().toLowerCase()))
                .filter(equipment -> engineTypes.contains(equipment.getEngineType().getName().toLowerCase()))
                .filter(equipment -> gearboxTypes.contains(equipment.getGearboxType().getName().toLowerCase()))
                .toList();

        loadEquipments(equipments);
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        flowPane.getChildren().clear();
        String searchText = searchBox.getText();
        List<Equipment> searchedEquipments = equipmentController.searchAutomobile(searchText);
        loadEquipments(searchedEquipments);
    }

}
