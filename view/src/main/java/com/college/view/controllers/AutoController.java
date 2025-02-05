package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
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
        pane.getStyleClass().add("equipment-pane");

        Label equipmentLabel = new Label(equipmentName);
        equipmentLabel.setLayoutY(20);
        equipmentLabel.setPrefWidth(260);
        equipmentLabel.setTextAlignment(TextAlignment.CENTER);
        equipmentLabel.setAlignment(Pos.CENTER);
        equipmentLabel.setFont(Font.font("Lucida Bright Demibold", 28));

        Label priceLabel = new Label(price + " $");
        priceLabel.setLayoutY(70);
        priceLabel.setPrefWidth(260);
        priceLabel.setTextAlignment(TextAlignment.CENTER);
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setFont(Font.font("Lucida Bright Demibold", 20));

        ListView<String> listView = new ListView<>();
        listView.setLayoutX(20);
        listView.setLayoutY(106);
        listView.setPrefSize(220, 206);
        listView.getItems().addAll(values);

        Button selectButton = new Button("Select");
        selectButton.setLayoutX(68);
        selectButton.setLayoutY(321);
        selectButton.setPrefSize(125, 40);
        selectButton.setFont(Font.font("Lucida Bright Demibold", 18));
        selectButton.setOnAction(this::onSelectClicked);

        pane.getChildren().addAll(equipmentLabel, priceLabel, listView, selectButton);

        return pane;
    }

    public void onSelectClicked(ActionEvent event) {
        Object clickedObject = event.getSource();
        Button button = (Button) clickedObject;
        Parent parent = button.getParent();
        if (parent instanceof Pane clikedPane) {
            flowPane.getChildren().stream()
                    .filter(node -> node.getStyleClass().getLast().equals("clicked-equipment-pane"))
                    .forEach(node -> node.getStyleClass().remove("clicked-equipment-pane"));

            clikedPane.getStyleClass().add("clicked-equipment-pane");
        }
    }

    public void buyButtonClicked(ActionEvent event) throws IOException {
        StageService.buildAndShowStage("Warranty", "warranty-form.fxml");
        StageService.closeCurrentStage(event);
    }

    public void cancelButtonClicked(ActionEvent event) throws IOException {
        StageService.buildAndShowStage("Home", "home-form.fxml");
        StageService.closeCurrentStage(event);
    }
}
