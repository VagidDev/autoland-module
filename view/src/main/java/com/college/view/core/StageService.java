package com.college.view.core;

import com.college.view.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class StageService {

    public static Stage getCurrentStageByEvent(Event event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static void closeCurrentStage(Event event) {
        Stage stage = getCurrentStageByEvent(event);
        stage.close();
    }

    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxml));
        loader.load();
        return loader;
    }

    public static Stage buildStage(String title, FXMLLoader fxmlLoader) throws IOException {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        return stage;
    }

    public static void buildAndShowStage(String title, String fxml) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = loadFXML(fxml);
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
