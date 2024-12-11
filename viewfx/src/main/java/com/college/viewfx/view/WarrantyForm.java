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

public class WarrantyForm {

    public void show() {
        // Создаём новое окно (Stage)
        Stage stage = new Stage();

        // Лейблы
        Label nameLabel = new Label("Название гарантии:");
        Label durationLabel = new Label("Продолжительность (в месяцах):");
        Label priceLabel = new Label("Цена (в валюте):");

        // Увеличиваем шрифт лейблов
        nameLabel.setStyle("-fx-font-size: 16;");
        durationLabel.setStyle("-fx-font-size: 16;");
        priceLabel.setStyle("-fx-font-size: 16;");
        
        // 
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        durationLabel.setMaxWidth(Double.MAX_VALUE);
        priceLabel.setMaxWidth(Double.MAX_VALUE);

        // Поля ввода
        TextField nameField = createTextField("Введите название гарантии");
        TextField durationField = createTextField("Введите количество месяцев");
        TextField priceField = createTextField("Введите цену");

        // Ограничение ввода на числовые значения для полей продолжительности и цены
        durationField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                durationField.setText(oldValue);
            }
        });

        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) { // Разрешаем до двух знаков после точки
                priceField.setText(oldValue);
            }
        });

        // Кнопки
        Button saveButton = new Button("Сохранить");
        Button cancelButton = new Button("Отменить");

        // Стили кнопок
        saveButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-padding: 10; -fx-font-size: 16;");
        cancelButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-padding: 10; -fx-font-size: 16;");

        HBox buttonsBox = new HBox(10, saveButton, cancelButton);
        buttonsBox.setAlignment(Pos.CENTER);

        saveButton.setOnAction(e -> {
            System.out.println("Сохранены данные гарантии!");
            System.out.println("Название: " + nameField.getText());
            System.out.println("Продолжительность: " + durationField.getText() + " месяцев");
            System.out.println("Цена: " + priceField.getText() + "");
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
                durationLabel, durationField,
                priceLabel, priceField,
                buttonsBox
        );

        // Настройка сцены и стадии
        Scene scene = new Scene(vBox, 400, 350);
        stage.setTitle("Добавление гарантии");
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

