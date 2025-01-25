package com.college.view.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class AccountController {
    @FXML
    private ImageView avatar;

    public void initialize() {
        Circle circle = new Circle(100, 100, 100);
        avatar.setClip(circle);
    }
}
