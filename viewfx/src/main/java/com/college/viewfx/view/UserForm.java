package com.college.viewfx.view;

import com.college.controller.UserController;
import com.college.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class UserForm {
    private final UserController controller;
    private final Runnable onFormSubmit;

    public UserForm(UserController controller, Runnable onFormSubmit) {
        this.controller = controller;
        this.onFormSubmit = onFormSubmit;
    }

    public void show() {
        Stage stage = new Stage();

        Label usernameLabel = createLabel("Логин:");
        TextField usernameField = createTextField("Введите логин");

        Label passwordLabel = createLabel("Пароль:");
        PasswordField passwordField = createPasswordField("Введите пароль");

        Label firstNameLabel = createLabel("Имя:");
        TextField firstNameField = createTextField("Введите имя");

        Label lastNameLabel = createLabel("Фамилия:");
        TextField lastNameField = createTextField("Введите фамилию");

        Label emailLabel = createLabel("Email:");
        TextField emailField = createTextField("Введите email");

        Label phoneLabel = createLabel("Телефон:");
        TextField phoneField = createTextField("+373___-__-___");
        phoneField.setText("+373");

        Label addressLabel = createLabel("Адрес:");
        TextField addressField = createTextField("Введите адрес");

        Label birthdayLabel = createLabel("Дата рождения:");
        DatePicker birthdayPicker = new DatePicker();
        birthdayPicker.setPromptText("Выберите дату рождения");
        birthdayPicker.setMaxWidth(Double.MAX_VALUE);

        Button saveButton = createButton("Сохранить", "-fx-background-color: green; -fx-text-fill: white;");
        Button cancelButton = createButton("Отменить", "-fx-background-color: red; -fx-text-fill: white;");

        saveButton.setOnAction(e -> {
            if (validateInputs(usernameField, passwordField, firstNameField, lastNameField, emailField, phoneField) && validateDatePicker(birthdayPicker)) {
                LocalDate birthday = birthdayPicker.getValue();
                boolean res = controller.saveUser(
                        usernameField.getText(),
                        passwordField.getText(),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        birthday != null ? birthday.toString() : null,
                        emailField.getText(),
                        phoneField.getText(),
                        addressField.getText()
                );
                if (res) {
                    new Alert(Alert.AlertType.INFORMATION, "Пользователь добавлен", ButtonType.OK).showAndWait();
                    onFormSubmit.run();
                    stage.close();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Ошибка при добавлении пользователя", ButtonType.OK).showAndWait();
                }
            }
        });

        cancelButton.setOnAction(e -> stage.close());

        HBox buttonPane = new HBox(15, saveButton, cancelButton);
        buttonPane.setAlignment(Pos.CENTER);

        VBox formLayout = new VBox(15,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                firstNameLabel, firstNameField,
                lastNameLabel, lastNameField,
                emailLabel, emailField,
                phoneLabel, phoneField,
                addressLabel, addressField,
                birthdayLabel, birthdayPicker,
                buttonPane
        );
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(formLayout, 400, 750);
        stage.setTitle("Добавление пользователя");
        stage.setScene(scene);
        stage.show();
    }

    private boolean validateInputs(TextField... fields) {
        boolean isValid = true;
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                field.setStyle("-fx-border-color: red;");
                isValid = false;
            } else {
                field.setStyle("");
            }
        }
        return isValid;
    }

    private boolean validateDatePicker(DatePicker datePicker) {
        if (datePicker.getValue() == null) {
            datePicker.setStyle("-fx-border-color: red;");
            return false;
        } else {
            datePicker.setStyle("");
            return true;
        }
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        label.setMaxWidth(Double.MAX_VALUE);
        return label;
    }

    private TextField createTextField(String placeholder) {
        TextField textField = new TextField();
        textField.setPromptText(placeholder);
        textField.setStyle("-fx-font-size: 14;");
        return textField;
    }

    private PasswordField createPasswordField(String placeholder) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(placeholder);
        passwordField.setStyle("-fx-font-size: 14;");
        return passwordField;
    }

    private Button createButton(String text, String style) {
        Button button = new Button(text);
        button.setStyle(style + " -fx-font-size: 14; -fx-padding: 10 20;");
        return button;
    }
}
