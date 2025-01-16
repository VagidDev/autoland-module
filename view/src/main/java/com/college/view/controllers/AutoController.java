package com.college.view.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.List;

public class AutoController {
    @FXML
    private FlowPane flowPane;

    @FXML
    public void initialize() {
        flowPane.getChildren().add(createEquipmentPane("Equip", 900000, Arrays.asList("Value 1", "Value 2", "Value 3", "Value 4")));
        flowPane.getChildren().add(createEquipmentPane("Equip", 900000, Arrays.asList("Value 1", "Value 2", "Value 3", "Value 4")));
        flowPane.getChildren().add(createEquipmentPane("Equip", 900000, Arrays.asList("Value 1", "Value 2", "Value 3", "Value 4")));
        flowPane.getChildren().add(createEquipmentPane("Equip", 900000, Arrays.asList("Value 1", "Value 2", "Value 3", "Value 4")));
        flowPane.getChildren().add(createEquipmentPane("Equip", 900000, Arrays.asList("Value 1", "Value 2", "Value 3", "Value 4")));
        flowPane.getChildren().add(createEquipmentPane("Equip", 900000, Arrays.asList("Value 1", "Value 2", "Value 3", "Value 4")));
        flowPane.getChildren().add(createEquipmentPane("Equip", 900000, Arrays.asList("Value 1", "Value 2", "Value 3", "Value 4")));
    }

    private Pane createEquipmentPane(String equipmentName, double price, List<String> values) {
        Pane pane = new Pane();
        pane.setPrefSize(260, 375);
        pane.getStyleClass().add("list-element");

        Label equipmentLabel = new Label(equipmentName);
        equipmentLabel.setLayoutY(20);
        equipmentLabel.setPrefWidth(260);
        equipmentLabel.setTextFill(Color.web("#a4a4a4"));
        equipmentLabel.setTextAlignment(TextAlignment.CENTER);
        equipmentLabel.setAlignment(Pos.CENTER);
        equipmentLabel.setFont(Font.font("Lucida Bright Demibold", 28));
        pane.getChildren().add(equipmentLabel);

        Label priceLabel = new Label(price + " $");
        priceLabel.setLayoutY(70);
        priceLabel.setPrefWidth(260);
        priceLabel.setTextFill(Color.web("#a4a4a4"));
        priceLabel.setTextAlignment(TextAlignment.CENTER);
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setFont(Font.font("Lucida Bright Demibold", 20));
        pane.getChildren().add(priceLabel);

        ListView<String> listView = new ListView<>();
        listView.setLayoutX(20);
        listView.setLayoutY(106);
        listView.setPrefSize(220, 206);
        listView.getItems().addAll(values);
        pane.getChildren().add(listView);

        Button selectButton = new Button("Select");
        selectButton.setLayoutX(68);
        selectButton.setLayoutY(321);
        selectButton.setPrefSize(125, 40);
        selectButton.setFont(Font.font("Lucida Bright Demibold", 18));
        pane.getChildren().add(selectButton);

        return pane;
    }
}
