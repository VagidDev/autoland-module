package com.college.view.core;

import com.college.view.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class StageService {

    public static void closeCurrentStage(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public static FXMLLoader loadFXMLLoader(String fxml) throws IOException {
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

    public static void buildSimpleStage(String title, String fxml) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
