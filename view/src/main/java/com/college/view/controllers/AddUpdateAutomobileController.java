package com.college.view.controllers;

import com.college.controller.AutomobileController;
import com.college.controller.SimpleTableController;
import com.college.controller.validators.automobile.AutomobileValidationResponse;
import com.college.model.entity.Automobile;
import com.college.model.entity.BodyType;
import com.college.model.entity.Warranty;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import com.college.view.core.TextFilters;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AddUpdateAutomobileController {
    @FXML private Label titleLabel;
    @FXML private TextField markField;
    @FXML private TextField modelField;
    @FXML private ComboBox<BodyType> bodyTypeComboBox;
    @FXML private TextField placeCountField;
    @FXML private TextField prodYearField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private SimpleTableController simpleTableController;
    private AutomobileController automobileController;

    public void initialize() {
        simpleTableController = ControllerManager.getSimpleTableController();
        automobileController = ControllerManager.getAutomobileController();

        if (AdminPanelContext.getAutomobileID() != -1) {
            titleLabel.setText("Update Automobile");

            Automobile automobile = automobileController.getAutomobileById(AdminPanelContext.getAutomobileID());
            markField.setText(automobile.getMark());
            modelField.setText(automobile.getModel());
            bodyTypeComboBox.setValue(automobile.getBodyType());
            placeCountField.setText(String.valueOf(automobile.getPlaceCount()));
            prodYearField.setText(String.valueOf(automobile.getProdYear()));
        }

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

        TextFormatter<String> placeCountFormatter = new TextFormatter<>(TextFilters.INTEGER_NUMBER_FILTER);
        TextFormatter<String> yearFormatter = new TextFormatter<>(TextFilters.INTEGER_NUMBER_FILTER);

        placeCountField.setTextFormatter(placeCountFormatter);
        prodYearField.setTextFormatter(yearFormatter);
    }

    public void onSave(ActionEvent actionEvent) {
        Automobile automobile = null;
        if (AdminPanelContext.getAutomobileID() == -1) {
            automobile = new  Automobile();
        } else {
            automobile = automobileController.getAutomobileById(AdminPanelContext.getAutomobileID());
        }

        automobile.setMark(markField.getText());
        automobile.setModel(modelField.getText());
        automobile.setBodyType(bodyTypeComboBox.getSelectionModel().getSelectedItem());
        if (placeCountField.getText().isEmpty()) automobile.setPlaceCount(0);
            else automobile.setPlaceCount(Integer.parseInt(placeCountField.getText()));
        if (prodYearField.getText().isEmpty()) automobile.setProdYear(0);
            else automobile.setProdYear(Integer.parseInt(prodYearField.getText()));

        AutomobileValidationResponse response = automobileController.validateAutomobile(automobile);
        if (response != AutomobileValidationResponse.VALID) {
            AlertHelper.invalidAutomobileDataAlert(response);
            return;
        }

        if (AdminPanelContext.getAutomobileID() == -1) {
            automobileController.createAutomobile(automobile);
            AlertHelper.showSimpleAlertDialog("Success", "New automobile was created!", Alert.AlertType.INFORMATION);
        } else {
            automobileController.editAutomobile(automobile);
            AlertHelper.showSimpleAlertDialog("Success", "New automobile was updated!", Alert.AlertType.INFORMATION);
        }
        AdminPanelContext.setAutomobileID(-1);
        ((Stage) saveButton.getScene().getWindow()).close();
    }

    public void onCancel(ActionEvent actionEvent) {
        AdminPanelContext.setAutomobileID(-1);
        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}
