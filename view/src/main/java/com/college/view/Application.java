package com.college.view;


import com.college.view.core.AnimationType;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
import com.college.view.core.StageService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneRouterService.build(stage);
        SceneRouterService.getSceneRouter().createFirstScene("authorization-form.fxml", "Autoland", AnimationType.ZOOM);
    }

    public static void main(String[] args) {
        ControllerManager.loadAllControllers();
        launch();
    }
}