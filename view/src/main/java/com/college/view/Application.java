package com.college.view;


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
        stage.setTitle("Authorization Form");
        stage.setScene(scene);
        stage.show();
        StageService.registerStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}