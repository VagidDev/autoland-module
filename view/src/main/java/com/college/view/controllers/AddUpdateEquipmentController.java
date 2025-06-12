package com.college.view.controllers;

import com.college.model.entity.*;
import com.college.view.core.SceneRouterService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

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
