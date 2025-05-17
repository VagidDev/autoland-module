package com.college.view.controllers;

import com.almasb.fxgl.cutscene.CutsceneService;
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

    private boolean isEditForm = false;

    @FXML
    public void initialize() {
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

    public void confirmButtonClicked(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        Date birthday = Date.from(birthdayField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        UserBuilder.setBasicInformation(name, surname, birthday, email, phone, address);

        if (isEditForm) {
            User currentUser = ControllerManager.getAuthorizationController().getCurrentUser();
            UserBuilder.setCredentials(currentUser.getLogin(), currentUser.getPassword());
            UserController controller = ControllerManager.getUserController();

            User editedUser = UserBuilder.buildUser();

            UserValidationResponse response = controller.validateUser(editedUser);

            if (response == UserValidationResponse.VALID) {
                editedUser.setId(currentUser.getId());
                editedUser.setAvatar(currentUser.getAvatar());
                controller.editUser(editedUser);

                ControllerManager.getAuthorizationController().editCurrentUserInfo(editedUser);

                AlertHelper.showSaveAlert("User Edited", "Your data has been updated!", Alert.AlertType.INFORMATION);
                SceneRouterService.getSceneRouter().switchToPreviousScene();
            } else {
                AlertHelper.invalidUserDataAlert(response);
            }

        } else {
            SceneRouterService.getSceneRouter().switchTo("registration-form.fxml", AnimationType.FADE);
        }
    }

    public void cancelButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
