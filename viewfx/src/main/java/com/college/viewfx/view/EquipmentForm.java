package com.college.viewfx.view;

import com.college.controller.EquipmentController;
import com.college.model.Automobile;
import com.college.model.Equipment;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class EquipmentForm {
    private Automobile automobile;
    private Equipment equipment;
    private EquipmentController controller;
    private Runnable onFormSubmit;

    public EquipmentForm(Automobile automobile, EquipmentController controller, Runnable onFormSubmit) {
        this.automobile = automobile;
        this.controller = controller;
        this.onFormSubmit = onFormSubmit;
    }

    public EquipmentForm(Equipment equipment, EquipmentController controller, Runnable onFormSubmit) {
        this.equipment = equipment;
        this.controller = controller;
        this.onFormSubmit = onFormSubmit;
    }

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
        addComboBox(contentBox, "Тип двигателя", controller.getEngineTypes());
        addTextField(contentBox, "Объем двигателя");
        addTextField(contentBox, "Лошадиные силы");
        addComboBox(contentBox, "Тип подвески", controller.getSuspensionTypes());
        addComboBox(contentBox, "Тип привода", controller.getDriveTypes());
        addComboBox(contentBox, "Тип КПП", controller.getGearboxTypes());
        addTextField(contentBox, "Количество скоростей");
        addComboBox(contentBox, "Тип топлива", controller.getFuelTypes());
        addTextField(contentBox, "Интерьер");
        addTextField(contentBox, "Обвес");
        addTextField(contentBox, "Вес");
        addTextField(contentBox, "Цена");

        scrollPane.setContent(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(600); // Устанавливаем предпочтительную высоту для ScrollPane

        if (equipment != null) {
            String[] values = new String[] {equipment.getName(), equipment.getEngineName(), equipment.getEngineType(), String.valueOf(equipment.getEngineVolume()),
                    String.valueOf(equipment.getHorsepower()), equipment.getSuspensiveType(), equipment.getDriveType(), equipment.getGearboxType(), String.valueOf(equipment.getSpeedCount()),
                    equipment.getFuelType(), equipment.getInterior(), equipment.getBodyKit(), String.valueOf(equipment.getWeigth()), String.valueOf(equipment.getPrice())};
            AtomicInteger counter = new AtomicInteger(0);
            contentBox.getChildren().forEach(node -> {
                if (node instanceof TextField) {
                    TextField textField = (TextField) node;
                    textField.setText(values[counter.getAndIncrement()]);
                }
                if (node instanceof ComboBox) {
                    ComboBox<String> comboBox = (ComboBox) node;
                    comboBox.setValue(values[counter.getAndIncrement()]);
                }
            });
        }

        // Создаем кнопки
        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            List<String> strings = new ArrayList<>();
            contentBox.getChildren().forEach(node -> {
                if (node instanceof TextField) {
                    strings.add(((TextField) node).getText());
                }
                if (node instanceof ComboBox) {
                    strings.add(((ComboBox<String>) node).getSelectionModel().getSelectedItem());
                }
            });

            boolean isProcessed = false;
            String processedText = null;

            if (equipment == null) {
                isProcessed = controller.saveEquipment(automobile, strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4), strings.get(5), strings.get(6), strings.get(7),
                        strings.get(8), strings.get(9), strings.get(10), strings.get(11), strings.get(12), strings.get(13));
                processedText = "Комплектация для " + automobile.getMark() + " " +  automobile.getModel() + " была добавлена!";
            } else {
                isProcessed = controller.updateEquipment(equipment, strings.get(0), strings.get(1), strings.get(2), strings.get(3), strings.get(4), strings.get(5), strings.get(6), strings.get(7),
                        strings.get(8), strings.get(9), strings.get(10), strings.get(11), strings.get(12), strings.get(13));
                processedText = "Комплектация обновлена!";
            }

            if (isProcessed) {
                new Alert(Alert.AlertType.INFORMATION, processedText, ButtonType.APPLY).showAndWait();
                onFormSubmit.run();
                primaryStage.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Ошибка");
            }



        });
        Button cancelButton = new Button("Отменить");
        cancelButton.setOnAction(actionEvent -> {
            primaryStage.close();
        });
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

    private void addComboBox(VBox container, String labelText, List<String> items) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px;");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setStyle("-fx-font-size: 14px;");
        comboBox.setMaxWidth(Double.MAX_VALUE);
        container.getChildren().addAll(label, comboBox);
    }

}