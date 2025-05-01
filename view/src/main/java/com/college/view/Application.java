package com.college.view;


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
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("authorization-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Autoland");
        stage.setScene(scene);
        stage.show();
        SceneRouterService.build(stage, 1600, 800);
        StageService.registerStage(stage);
    }

//    private static void loadApplication() {}

    public static void main(String[] args) {
        ControllerManager.loadAllControllers();
        launch();
    }
}