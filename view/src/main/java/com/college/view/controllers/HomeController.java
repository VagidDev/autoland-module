package com.college.view.controllers;

import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button dealersButton;
    @FXML
    private Button shoppingButton;
    @FXML
    private Button accountButton;

    @FXML
    public void onDealersButtonClicked(ActionEvent event) throws IOException {
        StageService.closeCurrentStage(event);
        StageService.buildAndShowStage("Dealers", "dealer-form.fxml");
    }

    @FXML
    public void onShoppingButtonClicked() {
        //some code
    }

    @FXML
    public void onAccountButtonClicked() {
        //some code
    }
}
