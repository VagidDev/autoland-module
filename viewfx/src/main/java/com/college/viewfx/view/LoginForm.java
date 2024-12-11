/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.viewfx.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Vagid Zibliuc
 */

public class LoginForm {

    public void show() {
        Stage primaryStage = new Stage();
        int prefSize = 250;
        Font font = new Font("Arial", 16);

        //labels
        Label usernameLabel = new Label("Login");
        Label passwordLabel = new Label("Password");

        usernameLabel.setPrefWidth(prefSize);
        passwordLabel.setPrefWidth(prefSize);

        usernameLabel.setAlignment(Pos.CENTER_LEFT);
        passwordLabel.setAlignment(Pos.CENTER_LEFT);

        usernameLabel.setFont(font);
        passwordLabel.setFont(font);

        //fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Input Login");
        usernameField.setFont(new Font("Arial", 14));
        usernameField.setPrefWidth(prefSize);
        

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("********");
        passwordField.setPrefWidth(prefSize);

        Button loginButton = new Button("Sing In");
        loginButton.setFont(font);
        loginButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-padding: 8;");
        loginButton.setPrefWidth(prefSize);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            System.out.println("Login: " + username);
            System.out.println("Password: " + password);
        });

        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        Scene scene = new Scene(vBox, 300, 250, Color.LIGHTGRAY);
        primaryStage.setTitle("Authorization Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
