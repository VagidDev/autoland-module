package com.college.view.controllers;

import com.college.controller.UserController;
import com.college.controller.validators.user.UserValidationResponse;
import com.college.model.entity.User;
import com.college.view.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class RegistrationController {
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private Button submitButton;


    private boolean isEditing = false;

    private UserValidationResponse updateUser(String login, String password, UserController userController) {
        User currentUser = ControllerManager.getAuthorizationController().getCurrentUser();
        currentUser.setLogin(login);
        currentUser.setPassword(password);

        UserValidationResponse response = userController.validateUser(currentUser);

        if (response == UserValidationResponse.VALID) {
            userController.editUser(currentUser);
        }

        return response;
    }

    private UserValidationResponse createUser(String login, String password, UserController userController) {
        UserBuilder.setCredentials(login, password);
        User user = UserBuilder.buildUser();

        UserValidationResponse response = userController.validateUser(user);

        if (response == UserValidationResponse.VALID) {
            userController.createUser(user);
        }

        return response;
    }

    @FXML
    private void initialize() {
        if (!SceneRouterService.getSceneRouter().getPreviousScene().equals("account-register-form.fxml")) {
            isEditing = true;
            loginField.setText(ControllerManager.getAuthorizationController().getCurrentUser().getLogin());
        }
    }

    public void submitButtonClicked(ActionEvent event) {
        UserController userController = ControllerManager.getUserController();

        String login = loginField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword) || password.isEmpty()) {
            AlertHelper.incorrectRegistrationCredentialsAlert();
            return;
        }

        UserValidationResponse response =
                isEditing ? updateUser(login, password, userController) : createUser(login, password, userController);

        if (response != UserValidationResponse.VALID) {
            AlertHelper.invalidUserDataAlert(response);
            return;
        }

        //TODO: make confirmation with input of your old password
        if (isEditing) {
            AlertHelper.showSaveAlert("Success Update", "Your login and password have been successfully updated!", Alert.AlertType.INFORMATION);
            SceneRouterService.getSceneRouter().switchToPreviousScene();
        } else {
            AlertHelper.showSaveAlert("Success Create", "You have successfully created an account!", Alert.AlertType.INFORMATION);
            SceneRouterService.getSceneRouter().switchTo("authorization-form.fxml", AnimationType.FADE);
        }

    }


    public void cancelButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
