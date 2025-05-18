package com.college.view.controllers;

import com.college.controller.UserController;
import com.college.controller.validators.user.UserValidationResponse;
import com.college.model.entity.User;
import com.college.view.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private boolean isEditForm = false;

    @FXML
    public void initialize() {
        setCustomDateConverter();

        if (SceneRouterService.getSceneRouter().getPreviousScene().equals("account-form.fxml")) {
            User user = ControllerManager.getAuthorizationController().getCurrentUser();
            nameField.setText(user.getName());
            surnameField.setText(user.getSurname());
            birthdayField.setValue(user.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            emailField.setText(user.getEmail());
            phoneField.setText(user.getTelephone());
            addressField.setText(user.getAddress());

            confirmButton.setText("Confirm");

            isEditForm = true;
        }

    }

    private void setCustomDateConverter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        birthdayField.setConverter(new StringConverter<>(){

            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    return formatter.format(localDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.trim().isEmpty()) {
                    return null;
                }
                try {
                    return LocalDate.parse(string, formatter);
                } catch (DateTimeParseException e) {
                    AlertHelper.showSimpleAlertDialog("Incorrect Date Format!", "You wrote your birthdate in incorrect date format\nPlease use 'DD.MM.YYYY'", Alert.AlertType.ERROR);
                    birthdayField.getEditor().setText("");
                    return null;
                }
            }
        });
    }

    public void confirmButtonClicked(ActionEvent event) {
        UserController controller = ControllerManager.getUserController();

        String name = nameField.getText();
        String surname = surnameField.getText();
        Date birthday = birthdayField.getValue() != null ? Date.from(birthdayField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        UserBuilder.setBasicInformation(name, surname, birthday, email, phone, address);
        User user = UserBuilder.buildUser();
        UserValidationResponse response = controller.validateUserInfo(user);

        if (response != UserValidationResponse.VALID) {
            AlertHelper.invalidUserDataAlert(response);
            return;
        }

        if (isEditForm) {
            User currentUser = ControllerManager.getAuthorizationController().getCurrentUser();
            user.setId(currentUser.getId());
            user.setLogin(currentUser.getLogin());
            user.setPassword(currentUser.getPassword());
            user.setRole(currentUser.getRole());
            user.setAvatar(currentUser.getAvatar());

            controller.editUser(user);
            ControllerManager.getAuthorizationController().editCurrentUserInfo(user);

            AlertHelper.showSimpleAlertDialog("User Edited", "Your data has been updated!", Alert.AlertType.INFORMATION);

            UserBuilder.clearAllData();
            SceneRouterService.getSceneRouter().switchToPreviousScene();
        } else {
            SceneRouterService.getSceneRouter().switchTo("registration-form.fxml", AnimationType.FADE);
        }
    }

    public void cancelButtonClicked(ActionEvent event) {
        UserBuilder.clearAllData();
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
