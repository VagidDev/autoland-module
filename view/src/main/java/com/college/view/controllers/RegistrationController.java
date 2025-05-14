package com.college.view.controllers;

import com.college.controller.UserController;
import com.college.controller.validators.user.UserValidationResponse;
import com.college.model.entity.User;
import com.college.view.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


    public void submitButtonClicked(ActionEvent event) {
        UserController userController = ControllerManager.getUserController();


        String login = loginField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        if (password.equals(confirmPassword) && !password.isEmpty()) {
            UserBuilder.setCredentials(login, password);
            User user = UserBuilder.buildUser();
            UserValidationResponse response = userController.validateUser(user);
            if (response == UserValidationResponse.VALID) {
                userController.createUser(user);
                //TODO: add logic for transferring data about user account
                SceneRouterService.getSceneRouter().switchTo("authorization-form.fxml", AnimationType.FADE);

                /*FXMLLoader loader = StageService.loadFXML("authorization-form.fxml");
                AuthorizationController controller = loader.getController();
                controller.setLoginAndPasswordFields(login, password);

                StageService.closeStageAndClearStack();
                StageService.buildStage("Authorization", loader).show();*/
            } else {
                AlertHelper.invalidUserDataAlert(response);
            }
        } else {
            AlertHelper.incorrectRegistrationCredentialsAlert();
        }
    }

    public void cancelButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
