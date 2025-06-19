package com.college.view.controllers;

import com.college.controller.AutomobileController;
import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.view.core.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class AutoController {
    @FXML private FlowPane flowPane;
    @FXML private Label modelLabel;
    @FXML private ScrollPane scrollPane;
    @FXML private ImageView carImageView;

    private AutomobileController automobileController;
    private List<Equipment> equipmentsForAuto;
    private int selectedEquipmentId = 0;

    @FXML
    public void initialize() {
        automobileController = ControllerManager.getAutomobileController();
        Automobile automobileForSell = ContractBuilder.getAutomobile();
        if (automobileForSell != null) {
            modelLabel.setText(automobileForSell.getMark() + " " + automobileForSell.getModel());
            equipmentsForAuto = automobileController.getEquipmentsByAutomobile(automobileForSell);

            equipmentsForAuto.forEach(equipment -> {
                flowPane.getChildren().add(createEquipmentPane(
                        equipment.getId().getEquipmentId(), equipment.getName(), equipment.getPrice(),
                        Arrays.asList(equipment.getShortEquipment())
                ));
            });
        }
    }

    private void loadCarImage(String imagePath) {
        if (imagePath != null) {
            carImageView.setImage(new Image(imagePath));
        } else {
            try {
                var path = Path.of("view/src/main/resources/images/test.jpg").toAbsolutePath();
                carImageView.setImage(new Image(Files.newInputStream(path)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private Pane createEquipmentPane(int equipmentId, String equipmentName, double price, List<String> values) {
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(0, 15, 10, 15));
        pane.setSpacing(10);
        pane.prefHeightProperty().bind(scrollPane.heightProperty().subtract(34));
        pane.getStyleClass().add("equipment-pane");

        pane.setId(String.valueOf(equipmentId));

        Label equipmentLabel = new Label(equipmentName);
        equipmentLabel.setPrefWidth(220);
        equipmentLabel.setTextAlignment(TextAlignment.CENTER);
        equipmentLabel.setAlignment(Pos.CENTER);
        equipmentLabel.setFont(Font.font("Lucida Bright Demibold", 28));

        Label priceLabel = new Label(price + " $");
        priceLabel.setPrefWidth(220);
        priceLabel.setTextAlignment(TextAlignment.CENTER);
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setFont(Font.font("Lucida Bright Demibold", 20));

        ListView<String> listView = new ListView<>();
        listView.setPrefWidth(220);
        listView.prefHeightProperty().bind(pane.heightProperty().subtract(150));
        listView.getItems().addAll(values);

        Button selectButton = new Button("Select");
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
            selectedEquipmentId = Integer.parseInt(clikedPane.getId());

            equipmentsForAuto.stream()
                    .filter(equipment -> equipment.getId().getEquipmentId() == selectedEquipmentId)
                    .findFirst().ifPresent(selectedEquipment -> loadCarImage(selectedEquipment.getImagePath()));

            flowPane.getChildren().stream()
                    .filter(node -> node.getStyleClass().getLast().equals("clicked-equipment-pane"))
                    .forEach(node -> node.getStyleClass().remove("clicked-equipment-pane"));

            clikedPane.getStyleClass().add("clicked-equipment-pane");
        }
    }

    public void buyButtonClicked(ActionEvent event) {
        if (selectedEquipmentId == 0) {
            AlertHelper.emptySelectionAlert();
            return;
        }
        ContractBuilder.setEquipmentById(selectedEquipmentId);
        SceneRouterService.getSceneRouter().switchTo("warranty-form.fxml", AnimationType.ZOOM);
    }

    public void cancelButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
