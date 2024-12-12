/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.viewfx.view;

import com.college.controller.UserController;
import com.college.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserForm {
    private User user;
    private UserController controller;
    private Runnable onFormSubmit;

    public UserForm(UserController controller, Runnable onFormSubmit) {
        this.controller = controller;
        this.onFormSubmit = onFormSubmit;
    }

    public void show() {
        // Создаём новое окно (Stage)
        Stage stage = new Stage();

        // Лейблы
        Label usernameLabel = new Label("Логин:");
        Label passwordLabel = new Label("Пароль:");
        Label firstNameLabel = new Label("Имя:");
        Label lastNameLabel = new Label("Фамилия:");
        Label emailLabel = new Label("Email:");
        Label phoneLabel = new Label("Номер телефона:");
        Label addressLabel = new Label("Адрес:");

        // Увеличиваем шрифт лейблов
        usernameLabel.setStyle("-fx-font-size: 16;");
        passwordLabel.setStyle("-fx-font-size: 16;");
        firstNameLabel.setStyle("-fx-font-size: 16;");
        lastNameLabel.setStyle("-fx-font-size: 16;");
        emailLabel.setStyle("-fx-font-size: 16;");
        phoneLabel.setStyle("-fx-font-size: 16;");
        addressLabel.setStyle("-fx-font-size: 16;");

        //
        usernameLabel.setMaxWidth(Double.MAX_VALUE);
        passwordLabel.setMaxWidth(Double.MAX_VALUE);
        firstNameLabel.setMaxWidth(Double.MAX_VALUE);
        lastNameLabel.setMaxWidth(Double.MAX_VALUE);
        emailLabel.setMaxWidth(Double.MAX_VALUE);
        phoneLabel.setMaxWidth(Double.MAX_VALUE);
        addressLabel.setMaxWidth(Double.MAX_VALUE);

        // Поля ввода
        TextField usernameField = createTextField("Введите логин");
        PasswordField passwordField = createPasswordField("Введите пароль");
        TextField firstNameField = createTextField("Введите имя");
        TextField lastNameField = createTextField("Введите фамилию");
        TextField emailField = createTextField("Введите email");
        TextField phoneField = createTextField("+373___-__-___");
        TextField addressField = createTextField("Введите адрес");

        // Маска для номера телефона
        phoneField.setText("+373");

        // Кнопки
        Button saveButton = new Button("Сохранить");
        Button cancelButton = new Button("Отменить");

        // Стили кнопок
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-padding: 10; -fx-font-size: 16;");
        cancelButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-padding: 10; -fx-font-size: 16;");

        HBox buttonsBox = new HBox(10, saveButton, cancelButton);
        buttonsBox.setAlignment(Pos.CENTER);

        saveButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String birthday = "2005-05-23";
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            boolean res = controller.saveUser(username, password, firstName, lastName, birthday, email, phone, address);
            if (res) {
                new Alert(Alert.AlertType.INFORMATION, "Пользователь добавлен", ButtonType.APPLY).showAndWait();
                onFormSubmit.run();
                stage.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Ошибка");
            }
        });

        cancelButton.setOnAction(e -> stage.close());

        // Размещение элементов в VBox
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.TOP_CENTER);

        // Добавляем элементы в VBox
        vBox.getChildren().addAll(
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                firstNameLabel, firstNameField,
                lastNameLabel, lastNameField,
                emailLabel, emailField,
                phoneLabel, phoneField,
                addressLabel, addressField,
                buttonsBox
        );

        // Настройка сцены и стадии
        Scene scene = new Scene(vBox, 400, 600);
        stage.setTitle("Добавление пользователя");
        stage.setScene(scene);
        stage.show();
    }

    // Метод для создания текстовых полей с плейсхолдерами и стилями
    private TextField createTextField(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.setStyle("-fx-font-size: 14;");
        textField.setMaxWidth(Double.MAX_VALUE);
        return textField;
    }

    // Метод для создания поля пароля с плейсхолдером и стилями
    private PasswordField createPasswordField(String placeholder) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(placeholder);
        passwordField.setStyle("-fx-font-size: 14;");
        passwordField.setMaxWidth(Double.MAX_VALUE);
        return passwordField;
    }

}
