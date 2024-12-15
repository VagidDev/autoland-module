package com.college.viewfx.view;

import com.college.controller.AutomobileController;
import com.college.model.Automobile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
        Stage stage = new Stage();

        stage.setTitle("Добавление автомобиля");

        Label brandLabel = createLabel("Марка авто:");
        TextField brandField = createTextField("Введите марку авто");

        Label modelLabel = createLabel("Модель автомобиля:");
        TextField modelField = createTextField("Введите модель автомобиля");

        Label bodyTypeLabel = createLabel("Тип кузова:");
        ComboBox<String> bodyTypeComboBox = new ComboBox<>();
        bodyTypeComboBox.getItems().addAll(controller.getBodyTypes());
        bodyTypeComboBox.setPromptText("Выберите тип кузова");
        bodyTypeComboBox.setStyle("-fx-font-size: 14");
        bodyTypeComboBox.setMaxWidth(Double.MAX_VALUE);

        Label seatsLabel = createLabel("Количество мест:");
        TextField seatsField = createTextField("Введите количество мест");

        Label yearLabel = createLabel("Год производства:");
        TextField yearField = createTextField("Введите год производства");

        Label imageLabel = createLabel("Изображение:");
        TextField imagePathField = createTextField("Выберите изображение");
        imagePathField.setEditable(false);
        imagePathField.setMaxWidth(Double.MAX_VALUE);
        Button chooseImageButton = new Button("Выберите картинку");
        chooseImageButton.setStyle("-fx-font-size: 14;");

        chooseImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите изображение автомобиля");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
            );
            java.io.File selectedFile = fileChooser.showOpenDialog(stage);
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

        Button saveButton = createButton("Сохранить", "-fx-background-color: green; -fx-text-fill: white;");
        Button cancelButton = createButton("Отменить", "-fx-background-color: red; -fx-text-fill: white;");

        saveButton.setOnAction(e -> {
            if (validateInputs(brandField, modelField, seatsField, yearField) && validateComboBox(bodyTypeComboBox)) {
                String brand = brandField.getText();
                String model = modelField.getText();
                String bodyType = bodyTypeComboBox.getValue();
                String seats = seatsField.getText();
                String year = yearField.getText();
                String imagePath = imagePathField.getText();

                boolean isProcessed;
                String processedText;

                if (automobile == null) {
                    isProcessed = controller.saveAutomobile(brand, model, bodyType, seats, year, imagePath);
                    processedText = "Автомобиль добавлен";
                } else {
                    isProcessed = controller.updateAutomobile(automobile, brand, model, bodyType, seats, year, imagePath);
                    processedText = "Автомобиль обновлен";
                }

                if (isProcessed) {
                    new Alert(Alert.AlertType.INFORMATION, processedText, ButtonType.OK).showAndWait();
                    onFormSubmit.run();
                    stage.close();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Ошибка при обработке данных", ButtonType.OK).showAndWait();
                }
            }
        });

        cancelButton.setOnAction(e -> stage.close());

        HBox imageButtons = new HBox(10, imagePathField, chooseImageButton);
        imageButtons.setMaxWidth(Double.MAX_VALUE);

        HBox confirmButtons = new HBox(10, saveButton, cancelButton);
        confirmButtons.setAlignment(Pos.CENTER);

        VBox formLayout = new VBox(15,
                brandLabel, brandField,
                modelLabel, modelField,
                bodyTypeLabel, bodyTypeComboBox,
                seatsLabel, seatsField,
                yearLabel, yearField,
                imageLabel, imageButtons,
                confirmButtons
        );
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(formLayout, 450, 550);
        stage.setTitle(automobile == null ? "Добавление автомобиля" : "Редактирование автомобиля");
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

    private boolean validateComboBox(ComboBox<String> comboBox) {
        if (comboBox.getValue() == null) {
            comboBox.setStyle("-fx-border-color: red;");
            return false;
        } else {
            comboBox.setStyle("");
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

    private Button createButton(String text, String style) {
        Button button = new Button(text);
        button.setStyle(style + " -fx-font-size: 14; -fx-padding: 10 20;");
        return button;
    }
}
