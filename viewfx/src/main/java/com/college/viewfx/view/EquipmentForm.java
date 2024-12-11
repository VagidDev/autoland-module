package com.college.viewfx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EquipmentForm {

    public void show() {

        Stage primaryStage = new Stage();

        primaryStage.setTitle("Добавление комплектации автомобиля");

        // Создаем корневой контейнер
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Создаем ScrollPane и VBox для полей ввода
        ScrollPane scrollPane = new ScrollPane();
        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(10));

        // Добавляем поля ввода
        addTextField(contentBox, "Название комплектации");
        addTextField(contentBox, "Название двигателя");
        addComboBox(contentBox, "Тип двигателя", "Бензиновый", "Дизельный", "Электрический", "Гибридный");
        addTextField(contentBox, "Объем двигателя");
        addTextField(contentBox, "Лошадиные силы");
        addComboBox(contentBox, "Тип подвески", "Пружинная", "Пневматическая", "Гидропневматическая");
        addComboBox(contentBox, "Тип привода", "Передний", "Задний", "Полный");
        addComboBox(contentBox, "Тип КПП", "Механическая", "Автоматическая", "Роботизированная", "Вариатор");
        addTextField(contentBox, "Количество скоростей");
        addComboBox(contentBox, "Тип топлива", "Бензин", "Дизель", "Электричество", "Гибрид");
        addTextField(contentBox, "Интерьер");
        addTextField(contentBox, "Бодикит");
        addTextField(contentBox, "Вес");
        addTextField(contentBox, "Цена");

        scrollPane.setContent(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(600); // Устанавливаем предпочтительную высоту для ScrollPane

        // Создаем кнопки
        Button saveButton = new Button("Сохранить");
        Button cancelButton = new Button("Отменить");
        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));

        root.getChildren().addAll(scrollPane, buttonBox);

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addTextField(VBox container, String labelText) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px;");
        TextField textField = new TextField();
        textField.setStyle("-fx-font-size: 14px;");
        textField.setMaxWidth(Double.MAX_VALUE);
        container.getChildren().addAll(label, textField);
    }

    private void addComboBox(VBox container, String labelText, String... items) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px;");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setStyle("-fx-font-size: 14px;");
        comboBox.setMaxWidth(Double.MAX_VALUE);
        container.getChildren().addAll(label, comboBox);
    }

}