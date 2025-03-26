package com.college.view.controllers;

import com.college.view.core.StageService;
import com.college.view.core.UserBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;


public class AccountRegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private DatePicker birthdayField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private Button confirmButton;

    public void confirmButtonClicked(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        Date birthday = Date.from(birthdayField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        UserBuilder.setBasicInformation(name, surname, birthday, email, phone, address);
        //TODO: should be another option, that will be activated if this form was called from AccountController
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Registration", "registration-form.fxml");
    }

    public void cancelButtonClicked(ActionEvent event) {
        StageService.closeStageAndOpenPrevious();
    }
}
