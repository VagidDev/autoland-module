package com.college.view.controllers;

import com.college.controller.AutomobileController;
import com.college.controller.SimpleTableController;
import com.college.model.entity.*;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

import java.io.File;

public class AddUpdateEquipmentController {

    @FXML private ImageView carImageView;
    @FXML private ComboBox<Automobile> automobileComboBox;
    @FXML private ComboBox<EngineType> engineTypeComboBox;
    @FXML private ComboBox<SuspensionType> suspensionTypeComboBox;
    @FXML private ComboBox<DriveType> driveTypeComboBox;
    @FXML private ComboBox<GearboxType> gearboxTypeComboBox;
    @FXML private ComboBox<FuelType> fuelTypeComboBox;
    @FXML private TextField nameTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField engineNameTextField;
    @FXML private TextField engineVolumeTextField;
    @FXML private TextField horsepowerTextField;
    @FXML private TextField speedTextField;
    @FXML private TextField interiorTextField;
    @FXML private TextField bodyKitTextField;
    @FXML private TextField weightTextField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private AutomobileController automobileController;
    private SimpleTableController simpleTableController;

    public void initialize() {
        automobileController = ControllerManager.getAutomobileController();
        simpleTableController = ControllerManager.getSimpleTableController();

        loadAutomobileComboBox();
        loadEngineTypeComboBox();
        loadDriveTypesComboBox();
        loadSuspensionTypesComboBox();
        loadFuelTypesComboBox();
        loadGearboxTypesComboBox();
    }

    private void loadAutomobileComboBox() {
        automobileComboBox.setItems(FXCollections.observableList(automobileController.getAllAutomobiles()));
        automobileComboBox.setConverter(new StringConverter<Automobile>() {
            @Override
            public String toString(Automobile automobile) {
                if (automobile == null) {
                    return null;
                }
                return automobile.getMark() + " " + automobile.getModel();
            }

            @Override
            public Automobile fromString(String s) {
                return automobileController
                        .getAllAutomobiles().stream()
                        .filter(automobile -> {
                            String searchString = automobile.getMark() + " " + automobile.getModel();
                            return searchString.equals(s);
                        }).findFirst().orElse(null);
            }
        });
    }

    private void loadEngineTypeComboBox() {
        engineTypeComboBox.setItems(FXCollections.observableList(simpleTableController.getEngineTypes()));
        engineTypeComboBox.setConverter(new StringConverter<EngineType>() {
            @Override
            public String toString(EngineType engineType) {
                return engineType.getName();
            }

            @Override
            public EngineType fromString(String s) {
                return simpleTableController
                        .getEngineTypes().stream()
                        .filter(engineType -> engineType.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });

    }

    private void loadDriveTypesComboBox() {
        driveTypeComboBox.setItems(FXCollections.observableList(simpleTableController.getDriveTypes()));
        driveTypeComboBox.setConverter(new StringConverter<DriveType>() {
            @Override
            public String toString(DriveType driveType) {
                return driveType.getName();
            }

            @Override
            public DriveType fromString(String s) {
                return simpleTableController
                        .getDriveTypes().stream()
                        .filter(driveType -> driveType.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });

    }

    private void loadSuspensionTypesComboBox() {
        suspensionTypeComboBox.setItems(FXCollections.observableList(simpleTableController.getSuspensionTypes()));
        suspensionTypeComboBox.setConverter(new StringConverter<SuspensionType>() {
            @Override
            public String toString(SuspensionType suspensionType) {
                return suspensionType.getName();
            }

            @Override
            public SuspensionType fromString(String s) {
                return simpleTableController
                        .getSuspensionTypes().stream()
                        .filter(suspensionType -> suspensionType.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });

    }

    private void loadFuelTypesComboBox() {
        fuelTypeComboBox.setItems(FXCollections.observableList(simpleTableController.getFuelTypes()));
        fuelTypeComboBox.setConverter(new StringConverter<FuelType>() {
            @Override
            public String toString(FuelType fuelType) {
                return fuelType.getName();
            }

            @Override
            public FuelType fromString(String s) {
                return simpleTableController
                        .getFuelTypes().stream()
                        .filter(fuelType -> fuelType.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });

    }

    private void loadGearboxTypesComboBox() {
        gearboxTypeComboBox.setItems(FXCollections.observableList(simpleTableController.getGearboxTypes()));
        gearboxTypeComboBox.setConverter(new StringConverter<GearboxType>() {
            @Override
            public String toString(GearboxType gearboxType) {
                return gearboxType.getName();
            }

            @Override
            public GearboxType fromString(String s) {
                return simpleTableController
                        .getGearboxTypes().stream()
                        .filter(gearboxType -> gearboxType.getName().equals(s))
                        .findFirst().orElse(null);
            }
        });

    }

    public void chooseAutomobilePhoto(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png")
                //new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(SceneRouterService.getSceneRouter().getCurrentStage()); // передай Stage текущего окна

        if (selectedFile != null) {
            carImageView.setImage(new Image(selectedFile.toURI().toString()));
            System.out.println("Выбран файл: " + selectedFile.getAbsolutePath());
        }
    }

}
