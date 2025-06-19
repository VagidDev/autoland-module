package com.college.view.controllers;

import com.college.controller.AutomobileController;
import com.college.controller.EquipmentController;
import com.college.controller.SimpleTableController;
import com.college.model.entity.*;
import com.college.model.entity.keys.EquipmentId;
import com.college.view.core.AdminPanelContext;
import com.college.view.core.AlertHelper;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;

public class AddUpdateEquipmentController {
    @FXML private Label titleLabel;
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
    private EquipmentController equipmentController;
    private SimpleTableController simpleTableController;

    public void initialize() {
        automobileController = ControllerManager.getAutomobileController();
        simpleTableController = ControllerManager.getSimpleTableController();

        equipmentController = ControllerManager.getEquipmentController();
        if (AdminPanelContext.getEquipmentID() != null) {
            titleLabel.setText("Update Equipment");

            Equipment equipment =  equipmentController.getEquipment(AdminPanelContext.getEquipmentID());
            nameTextField.setText(equipment.getName());
            priceTextField.setText(String.valueOf(equipment.getPrice()));
            engineNameTextField.setText(equipment.getEngineName());
            engineVolumeTextField.setText(String.valueOf(equipment.getEngineVolume()));
            horsepowerTextField.setText(String.valueOf(equipment.getHorsepower()));
            speedTextField.setText(String.valueOf(equipment.getSpeedCount()));
            interiorTextField.setText(equipment.getInterior());
            bodyKitTextField.setText(equipment.getBodyKit());
            weightTextField.setText(String.valueOf(equipment.getWeight()));
            automobileComboBox.getSelectionModel().select(equipment.getAutomobile());
            engineTypeComboBox.getSelectionModel().select(equipment.getEngineType());
            suspensionTypeComboBox.getSelectionModel().select(equipment.getSuspensionType());
            driveTypeComboBox.getSelectionModel().select(equipment.getDriveType());
            gearboxTypeComboBox.getSelectionModel().select(equipment.getGearboxType());
            fuelTypeComboBox.getSelectionModel().select(equipment.getFuelType());
            //image loading
            if (equipment.getImagePath() != null) {
                try {
                    carImageView.setImage(new Image(equipment.getImagePath()));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }

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

    public void onSave(ActionEvent actionEvent) {
        Equipment equipment = null;
        if (AdminPanelContext.getEquipmentID() == null) {
            equipment = new  Equipment();
        } else {
            equipment = equipmentController.getEquipment(AdminPanelContext.getEquipmentID());
        }
        equipment.setName(nameTextField.getText());
        equipment.setPrice(Double.parseDouble(priceTextField.getText()));
        equipment.setEngineName(engineNameTextField.getText());
        equipment.setEngineVolume(Float.parseFloat(engineVolumeTextField.getText()));
        equipment.setHorsepower(Integer.parseInt(horsepowerTextField.getText()));
        equipment.setSpeedCount(Integer.parseInt(speedTextField.getText()));
        equipment.setInterior(interiorTextField.getText());
        equipment.setBodyKit(bodyKitTextField.getText());
        equipment.setWeight(Integer.parseInt(weightTextField.getText()));
        equipment.setAutomobile(automobileComboBox.getSelectionModel().getSelectedItem());
        equipment.setSuspensionType(suspensionTypeComboBox.getSelectionModel().getSelectedItem());
        equipment.setFuelType(fuelTypeComboBox.getSelectionModel().getSelectedItem());
        equipment.setGearboxType(gearboxTypeComboBox.getSelectionModel().getSelectedItem());
        equipment.setEngineType(engineTypeComboBox.getSelectionModel().getSelectedItem());
        equipment.setDriveType(driveTypeComboBox.getSelectionModel().getSelectedItem());
        equipment.setImagePath(carImageView.getImage().getUrl());

        if (AdminPanelContext.getEquipmentID() == null) {
            equipmentController.createEquipment(equipment);
            AlertHelper.showSimpleAlertDialog("Success", "New equipment for " + equipment.getAutomobile().getMark()
                    + " " + equipment.getAutomobile().getModel() + " was created!", Alert.AlertType.INFORMATION);
        } else {
            equipmentController.editEquipment(equipment);
            AlertHelper.showSimpleAlertDialog("Success", "Equipment for " + equipment.getAutomobile().getMark()
                    + " " + equipment.getAutomobile().getModel() + " was updated!", Alert.AlertType.INFORMATION);
        }
        AdminPanelContext.setEquipmentID(null);
        ((Stage) saveButton.getScene().getWindow()).close();
    }

    public void onCancel(ActionEvent actionEvent) {
        AdminPanelContext.setEquipmentID(null);
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

}
