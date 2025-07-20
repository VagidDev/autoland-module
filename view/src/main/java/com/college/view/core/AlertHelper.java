package com.college.view.core;

import com.college.controller.validators.automobile.AutomobileValidationResponse;
import com.college.controller.validators.dealer.DealerValidationResponse;
import com.college.controller.validators.user.UserValidationResponse;
import com.college.controller.validators.warranty.WarrantyValidationResponse;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public abstract class AlertHelper {

    public static void invalidUserDataAlert(UserValidationResponse response) {
        String responseText = switch (response) {
            case INVALID_LOGIN -> "Empty login! Login cannot be empty!";
            case INVALID_PASSWORD -> "Incorrect password! Please insert at least 8 symbols";
            case INVALID_ADDRESS -> "Empty address! Your address cannot be empty";
            case INVALID_EMAIL -> "Incorrect email! Please enter a valid email address";
            case INVALID_NAME -> "Empty name! Please enter your name";
            case INVALID_SURNAME -> "Empty surname! Please enter a valid surname";
            case INVALID_BIRTHDATE -> "Incorrect date! Please enter a valid date";
            case INVALID_PHONE -> "Incorrect phone! Please enter a valid phone(+373________)";
            case LOGIN_EXISTS ->  "This login is already taken, please choose another one!";
            default -> "Incorrect registration credentials!";
        };

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid User Data");
        alert.setHeaderText(null);
        alert.setContentText(responseText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static void invalidDealerDataAlert(DealerValidationResponse response) {
        String responseText = switch (response) {
            case INVALID_ADDRESS -> "Empty address! Dealer should has address";
            case INVALID_NAME -> "Empty name! Please enter dealer name";
            case INVALID_PHONE, INVALID_FAX_PHONE -> "Incorrect phone! Please enter a valid phone(+373________)";
            default -> "Incorrect Dealer Data!";
        };

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Dealer Data");
        alert.setHeaderText(null);
        alert.setContentText(responseText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static void invalidWarrantyDataAlert(WarrantyValidationResponse response) {
        String responseText = switch (response) {
            case INVALID_NAME -> "Empty name! Please enter warranty name";
            case INVALID_DURATION -> "Incorrect duration! Please enter duration bigger then 0!";
            case INVALID_PRICE -> "Incorrect price! Please enter price bigger then 0!";
            default -> "Incorrect Warranty Data!";
        };

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Warranty Data");
        alert.setHeaderText(null);
        alert.setContentText(responseText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static void invalidAutomobileDataAlert(AutomobileValidationResponse response) {
        String responseText = switch (response) {
            case INVALID_MARK -> "Empty mark! Please enter mark";
            case INVALID_MODEL -> "Empty model! Please enter model";
            case INVALID_BODY_TYPE -> "Empty body type! Please select body type";
            case INVALID_PLACE_COUNT -> "Incorrect place count! Please enter place count bigger then 0";
            case INVALID_PRODUCTION_YEAR -> "Incorrect production year! Please a year between current year and 1888";
            default -> "Incorrect automobile credentials!";
        };

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Automobile Data");
        alert.setHeaderText(null);
        alert.setContentText(responseText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static void incorrectRegistrationCredentialsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Registration Credentials");
        alert.setHeaderText(null);
        alert.setContentText("Different passwords!");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static void emptySelectionAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty Selection");
        alert.setHeaderText(null);
        alert.setContentText("Please choose some option to continue");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static void showInvalidLoginAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Authorization Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect login or password. Please try again.");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void showSimpleAlertDialog(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

}
