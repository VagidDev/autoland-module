package com.college.view.core;

import com.college.controller.validators.automobile.AutomobileValidationResponse;
import com.college.controller.validators.contract.ContractValidatorResponse;
import com.college.controller.validators.dealer.DealerValidationResponse;
import com.college.controller.validators.equipment.EquipmentValidationResponse;
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

    public static void invalidEquipmentDataAlert(EquipmentValidationResponse response) {
        String responseText = switch (response) {
            case INVALID_NAME -> "Empty name! Please enter name";
            case INVALID_AUTOMOBILE -> "No automobile! Please choose an automobile";
            case INVALID_ENGINE_NAME -> "Empty engine name! Please enter engine name";
            case INVALID_ENGINE_TYPE -> "No engine type! Please choose an engine type";
            case INVALID_ENGINE_VOLUME -> "Volume is less then 0 or bigger then 50! Please enter valid engine volume";
            case INVALID_HORSEPOWER -> "Horsepower less then 0 or equals 0! Please enter valid horsepower";
            case INVALID_SUSPENSION_TYPE -> "No suspension type! Please choose a suspension type";
            case INVALID_DRIVE_TYPE -> "No drive type! Please choose a drive type";
            case INVALID_GEARBOX_TYPE -> "No gearbox type! Please choose a gearbox type";
            case INVALID_SPEED_COUNT -> "Speed count less then 0 or more then 50! Please enter valid speed count";
            case INVALID_FUEL_TYPE -> "No fuel type! Please choose a fuel type";
            case INVALID_INTERIOR -> "Empty interior! Please choose interior";
            case INVALID_BODY_KIT -> "Empty body kit! Please enter body kit";
            case INVALID_WEIGHT -> "Weight is less then or equals to 0! Please enter valid weight";
            case INVALID_PRICE -> "Price is less then or equals to 0 or bigger then 9999999.99! Please enter valid price";
            default -> "Incorrect equipment data!";
        };

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Equipment Data");
        alert.setHeaderText(null);
        alert.setContentText(responseText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.showAndWait();
    }

    public static void invalidContractDataAlert(ContractValidatorResponse response) {
        String responseText = switch (response) {
            case INVALID_USER -> "This user does not exists! Please select a user";
            case INVALID_DEALER -> "Empty dealer! Please select a dealer";
            case INVALID_WARRANTY -> "Empty warranty! Please select a warranty";
            case INVALID_EQUIPMENT -> "Incorrect equipment! Please select automobile and equipment";
            case INVALID_CONCLUSION_DATE -> "Incorrect conclusion date! Please enter date bigger than 1888 and less or equals to today";
            default -> "Incorrect contract credentials!";
        };

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid contract Data");
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
