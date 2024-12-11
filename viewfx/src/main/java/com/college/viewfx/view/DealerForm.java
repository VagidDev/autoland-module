/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.viewfx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Vagid Zibliuc
 */

public class DealerForm {

    public void show() {
        // Создаём новое окно (Stage)
        Stage stage = new Stage();

        // Лейблы
        Label nameLabel = new Label("Название:");
        Label addressLabel = new Label("Адрес:");
        Label phoneLabel = new Label("Телефон:");
        Label faxLabel = new Label("Телефон-факс:");
        
        // Увеличиваем шрифт лейблов
        nameLabel.setStyle("-fx-font-size: 16;");
        addressLabel.setStyle("-fx-font-size: 16;");
        phoneLabel.setStyle("-fx-font-size: 16;");
        faxLabel.setStyle("-fx-font-size: 16;");

        //
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        addressLabel.setMaxWidth(Double.MAX_VALUE);
        phoneLabel.setMaxWidth(Double.MAX_VALUE);
        faxLabel.setMaxWidth(Double.MAX_VALUE);

        // Поля ввода
        TextField nameField = createTextField("Введите название");
        TextField addressField = createTextField("Введите адрес");

        TextField phoneField = createTextField("+373(XXX)-XX-XX");

        TextField faxField = createTextField("+373(XXX)-XX-XX");

        // Кнопки
        Button saveButton = new Button("Сохранить");
        Button cancelButton = new Button("Отменить");

        // Стили кнопок
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-padding: 10; -fx-font-size: 16;");
        cancelButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-padding: 10; -fx-font-size: 16;");

        HBox buttonsBox = new HBox(10, saveButton, cancelButton);
        buttonsBox.setAlignment(Pos.CENTER);

        saveButton.setOnAction(e -> {
            System.out.println("Сохранены данные дилера!");
            System.out.println("Название: " + nameField.getText());
            stage.close();
        });

        cancelButton.setOnAction(e -> stage.close());

        // Размещение элементов в VBox
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20));
        vBox.setAlignment(Pos.TOP_CENTER);

        // Добавляем элементы в VBox
        vBox.getChildren().addAll(
                nameLabel, nameField,
                addressLabel, addressField,
                phoneLabel, phoneField,
                faxLabel, faxField,
                buttonsBox
        );

        // Настройка сцены и стадии
        Scene scene = new Scene(vBox, 400, 400);
        stage.setTitle("Добавление дилера");
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

    
}

