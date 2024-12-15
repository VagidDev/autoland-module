package com.college.viewfx.view;

import com.college.controller.DealerController;
import com.college.model.Dealer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DealerForm {
    private Dealer dealer;
    private DealerController dealerController;
    private Runnable onFormSubmit;

    public DealerForm(DealerController dealerController, Runnable onFormSubmit) {
        this.dealerController = dealerController;
        this.onFormSubmit = onFormSubmit;
    }

    public DealerForm(Dealer dealer, DealerController dealerController, Runnable onFormSubmit) {
        this.dealer = dealer;
        this.dealerController = dealerController;
        this.onFormSubmit = onFormSubmit;
    }

    public void show() {
        Stage stage = new Stage();

        // Labels and Input Fields
        Label nameLabel = createLabel("Название:");
        TextField nameField = createTextField("Введите название");

        Label addressLabel = createLabel("Адрес:");
        TextField addressField = createTextField("Введите адрес");

        Label phoneLabel = createLabel("Телефон:");
        TextField phoneField = createTextField("+373(XXX)-XX-XX");

        Label faxLabel = createLabel("Телефон-факс:");
        TextField faxField = createTextField("+373(XXX)-XX-XX");

        if (dealer != null) {
            nameField.setText(dealer.getName());
            addressField.setText(dealer.getAddress());
            phoneField.setText(dealer.getTelephone());
            faxField.setText(dealer.getFax());
        }

        // Buttons
        Button saveButton = createButton("Сохранить", "-fx-background-color: green; -fx-text-fill: white;");
        Button cancelButton = createButton("Отменить", "-fx-background-color: red; -fx-text-fill: white;");

        saveButton.setOnAction(e -> {
            if (validateInputs(nameField, addressField, phoneField, faxField)) {
                String name = nameField.getText();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String fax = faxField.getText();

                boolean isProcessed;
                String processedText;

                if (dealer == null) {
                    isProcessed = dealerController.saveDealer(name, address, phone, fax);
                    processedText = "Дилер добавлен";
                } else {
                    isProcessed = dealerController.updateDealer(dealer, name, address, phone, fax);
                    processedText = "Дилер обновлен";
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
        HBox buttonPane = new HBox(15, saveButton, cancelButton);
        buttonPane.setAlignment(Pos.CENTER);
        // Layout
        VBox formLayout = new VBox(15,
                nameLabel, nameField,
                addressLabel, addressField,
                phoneLabel, phoneField,
                faxLabel, faxField,
                buttonPane
        );
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(formLayout, 400, 500);
        stage.setTitle(dealer == null ? "Добавление дилера" : "Редактирование дилера");
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
