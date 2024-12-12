package com.college.viewfx;

import com.college.viewfx.view.*;
import javafx.application.Application;

import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {



    @Override
    public void start(Stage primaryStage) {
//
        AdminWindow adminWindow = new AdminWindow();
        adminWindow.show();
    }


    public static void main(String[] args) {
        launch();
    }

}