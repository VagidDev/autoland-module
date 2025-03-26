package com.college.view.core;

import com.college.view.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class StageService {

    private final static List<Stage> stages = new LinkedList<>();

    public static FXMLLoader loadFXML(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxml));
            loader.load();
            return loader;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML", e);
        }
    }

    public static Stage buildStage(String title, FXMLLoader fxmlLoader) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        stages.add(stage);
        return stage;
    }

    public static void buildAndShowStage(String title, String fxml) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = loadFXML(fxml);
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        stages.add(stage);
    }

    public static void registerStage(Stage stage) {
        stages.add(stage);
    }

    public static Stage getCurrentStage() {
        return stages.getLast();
    }

    public static Stage getPreviousStage() {
        int countElements = stages.size();
        if (countElements < 2) {
            return stages.getLast();
        }
        return stages.get(countElements - 2);
    }

    public static void closeStage() {
        stages.removeLast().close();
    }

    public static void closeAndSaveStage() {
        stages.getLast().close();
    }

    public static void closeStageAndOpenPrevious() {
        stages.removeLast().close();
        stages.getLast().show();
    }

    public static void closeStageAndClearStack() {
        stages.getLast().close();
        stages.clear();
    }

    public static void unregisterStage(Stage stage) {
        stages.remove(stage);
    }

}
