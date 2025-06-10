package com.college.view.controllers;

import com.college.view.core.SceneRouterService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class AddUpdateFormController {
    public void chooseAvatar(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png")
                //new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );

        File selectedFile = fileChooser.showOpenDialog(SceneRouterService.getSceneRouter().getCurrentStage()); // передай Stage текущего окна

        if (selectedFile != null) {
            System.out.println("Выбран файл: " + selectedFile.getAbsolutePath());
        }
    }
}
