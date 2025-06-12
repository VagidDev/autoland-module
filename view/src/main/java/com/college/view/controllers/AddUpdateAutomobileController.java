package com.college.view.controllers;

import com.college.controller.SimpleTableController;
import com.college.model.entity.BodyType;
import com.college.view.core.ControllerManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class AddUpdateAutomobileController {

    @FXML private TextField markField;
    @FXML private TextField modelField;
    @FXML private ComboBox<BodyType> bodyTypeComboBox;
    @FXML private TextField placeCountField;
    @FXML private TextField prodYearField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private SimpleTableController simpleTableController;

    public void initialize() {
        simpleTableController = ControllerManager.getSimpleTableController();

        bodyTypeComboBox.setItems(FXCollections.observableList(simpleTableController.getBodyTypes()));
        bodyTypeComboBox.setConverter(new StringConverter<BodyType>() {
            @Override
            public String toString(BodyType bodyType) {
                return bodyType.getName() == null ? null : bodyType.getName();
            }

            @Override
            public BodyType fromString(String s) {
                return simpleTableController
                        .getBodyTypes().stream()
                        .filter(bodyType -> bodyType.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });
    }
}
