package com.college.view.controllers;

import com.college.controller.UserController;
import com.college.controller.validators.user.UserValidationResponse;
import com.college.model.entity.User;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.ZoneId;
import java.util.Date;

public class AddUpdateUserController {
    @FXML private Label titleLabel;
    @FXML private TextField loginTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private DatePicker birthdayDatePicker;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField imageTextField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private UserController userController;

    public void initialize() {
        userController = ControllerManager.getUserController();
        if (AdminPanelContext.getUserID() != -1) {
            titleLabel.setText("Update User");

            User user = userController.getUser(AdminPanelContext.getUserID());
            loginTextField.setText(user.getLogin());
            passwordTextField.setText(user.getPassword());
            nameTextField.setText(user.getName());
            surnameTextField.setText(user.getSurname());
            birthdayDatePicker.setValue(user.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            emailTextField.setText(user.getEmail());
            phoneTextField.setText(user.getTelephone());
            phoneTextField.setText(user.getTelephone());
            addressTextField.setText(user.getAddress());
            imageTextField.setText(user.getAvatar());
        }

    }

    public void onSave(ActionEvent actionEvent) {
        User user = null;
        if (AdminPanelContext.getUserID() == -1) {
            user = new User();
        } else {
            user = userController.getUser(AdminPanelContext.getUserID());
        }

        Date birthday = birthdayDatePicker.getValue() != null ? Date.from(birthdayDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;

        user.setLogin(loginTextField.getText());
        user.setPassword(passwordTextField.getText());
        user.setName(nameTextField.getText());
        user.setSurname(surnameTextField.getText());
        user.setBirthday(birthday);
        user.setEmail(emailTextField.getText());
        user.setTelephone(phoneTextField.getText());
        user.setAddress(addressTextField.getText());
        user.setAvatar(imageTextField.getText());

        UserValidationResponse response = userController.validateUser(user);

        if (response == UserValidationResponse.VALID) {
            if (AdminPanelContext.getUserID() == -1) {
                userController.createUser(user);
                AlertHelper.showSimpleAlertDialog("Success", "New user was created!", Alert.AlertType.INFORMATION);
            } else {
                userController.editUser(user);
                AlertHelper.showSimpleAlertDialog("Success", "New user was updated!", Alert.AlertType.INFORMATION);
            }
            AdminPanelContext.setUserID(-1);
            ((Stage) saveButton.getScene().getWindow()).close();
        } else {
            AlertHelper.invalidUserDataAlert(response);
        }
    }

    public void onCancel(ActionEvent actionEvent) {
        AdminPanelContext.setUserID(-1);
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    public void chooseAvatar(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png")
        );

        File selectedFile = fileChooser.showOpenDialog(SceneRouterService.getSceneRouter().getCurrentStage()); // передай Stage текущего окна

        if (selectedFile != null) {
            imageTextField.setText(selectedFile.toURI().toString());
        }
    }
}
