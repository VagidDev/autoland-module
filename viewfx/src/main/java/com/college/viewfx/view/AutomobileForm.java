/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.viewfx.view;

/**
 *
 * @author Vagid Zibliuc
 */
import com.college.controller.AutomobileController;
import com.college.model.Automobile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AutomobileForm {
    private Automobile automobile;
    private AutomobileController controller;
    private Runnable onFormSubmit;

    public AutomobileForm(AutomobileController controller, Runnable onFormSubmit) {
        this.controller = controller;
        this.onFormSubmit = onFormSubmit;
    }

    public AutomobileForm(Automobile automobile, AutomobileController controller, Runnable onFormSubmit) {
        this.automobile = automobile;
        this.controller = controller;
        this.onFormSubmit = onFormSubmit;
    }

    public void show() {

        Stage primaryStage = new Stage();

        primaryStage.setTitle("Добавление автомобиля");

        AnchorPane root = new AnchorPane();

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(columnConstraints);
        

        Label brandLabel = new Label("Марка авто:");
        brandLabel.setStyle("-fx-font-size: 16px;");
        TextField brandField = new TextField();
        brandField.setStyle("-fx-font-size: 14px;");
        grid.add(brandLabel, 0, 0);
        grid.add(brandField, 0, 1);

        Label modelLabel = new Label("Модель автомобиля:");
        modelLabel.setStyle("-fx-font-size: 16px;");
        TextField modelField = new TextField();
        modelField.setStyle("-fx-font-size: 14px;");
        grid.add(modelLabel, 0, 2);
        grid.add(modelField, 0, 3);

        Label bodyTypeLabel = new Label("Тип кузова:");
        bodyTypeLabel.setStyle("-fx-font-size: 16px;");
        ComboBox<String> bodyTypeComboBox = new ComboBox<>();
        bodyTypeComboBox.setStyle("-fx-font-size: 14px;");
        bodyTypeComboBox.getItems().addAll(controller.getBodyTypes());
        grid.add(bodyTypeLabel, 0, 4);
        grid.add(bodyTypeComboBox, 0, 5);

        Label seatsLabel = new Label("Количество мест:");
        seatsLabel.setStyle("-fx-font-size: 16px;");
        TextField seatsField = new TextField();
        seatsField.setStyle("-fx-font-size: 14px;");
        grid.add(seatsLabel, 0, 6);
        grid.add(seatsField, 0, 7);

        Label yearLabel = new Label("Год производства:");
        yearLabel.setStyle("-fx-font-size: 16px;");
        TextField yearField = new TextField();
        yearField.setStyle("-fx-font-size: 14px;");
        grid.add(yearLabel, 0, 8);
        grid.add(yearField, 0, 9);

        Label imageLabel = new Label("Путь к изображению:");
        imageLabel.setStyle("-fx-font-size: 16px;");
        TextField imagePathField = new TextField();
        imagePathField.setStyle("-fx-font-size: 14px;");
        imagePathField.setEditable(false);
        Button chooseImageButton = new Button("Выбрать");
        chooseImageButton.setStyle("-fx-font-size: 14px;");
        HBox imageChooser = new HBox(10);
        imageChooser.getChildren().add(imagePathField);
        imageChooser.getChildren().add(chooseImageButton);
        
        grid.add(imageLabel, 0, 10);
        grid.add(imageChooser, 0, 11);

        chooseImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите изображение автомобиля");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            java.io.File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imagePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        if (automobile != null) {
            brandField.setText(automobile.getMark());
            modelField.setText(automobile.getModel());
            bodyTypeComboBox.setValue(automobile.getBodyType());
            seatsField.setText(String.valueOf(automobile.getPlaceCount()));
            yearField.setText(String.valueOf(automobile.getProdYear()));
            imagePathField.setText(automobile.getImagePath());
        }

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> {
            String brand = brandField.getText();
            String model = modelField.getText();
            String bodyType = bodyTypeComboBox.getValue();
            String seats = seatsField.getText();
            String year = yearField.getText();
            String imagePath = imagePathField.getText();

            boolean isProcessed = false;
            String processedText = null;

            if (automobile == null) {
                isProcessed = controller.saveAutomobile(brand, model, bodyType, seats, year, imagePath);
                processedText = "Автомобиль добавлен";
            } else {
                isProcessed = controller.updateAutomobile(automobile, brand, model, bodyType, seats, year, imagePath);
                processedText = "Автомобиль обновлен";
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
        saveButton.setStyle("-fx-font-size: 14px;");
        cancelButton.setStyle("-fx-font-size: 14px;");
        HBox formButtons = new HBox(30);
        formButtons.getChildren().add(saveButton);
        formButtons.getChildren().add(cancelButton);
        formButtons.setAlignment(Pos.CENTER);
        HBox.setHgrow(imagePathField, Priority.ALWAYS);
        chooseImageButton.setMaxWidth(Double.MAX_VALUE);
        imageChooser.setMaxWidth(Double.MAX_VALUE);
        grid.add(formButtons, 0, 12);

        // Растягиваем поля ввода на всю ширину
        brandField.setMaxWidth(Double.MAX_VALUE);
        modelField.setMaxWidth(Double.MAX_VALUE);
        bodyTypeComboBox.setMaxWidth(Double.MAX_VALUE);
        seatsField.setMaxWidth(Double.MAX_VALUE);
        yearField.setMaxWidth(Double.MAX_VALUE);
        imagePathField.setMaxWidth(Double.MAX_VALUE);
        imageChooser.setMaxWidth(Double.MAX_VALUE);
        formButtons.setMaxWidth(Double.MAX_VALUE);
        
        grid.setMaxWidth(Double.MAX_VALUE);
        grid.setPrefWidth(Double.MAX_VALUE);

        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);

        root.getChildren().add(grid);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
