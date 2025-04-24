package com.college.view.controllers;

import com.college.view.core.ControllerManager;
import com.college.view.core.StageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
    @FXML
    private Button dealersButton;
    @FXML
    private Button shoppingButton;
    @FXML
    private Button accountButton;
    @FXML
    private Button reportsButton;

    @FXML
    private void initialize() {
        if (!ControllerManager.getAuthorizationController().getCurrentUser().getRole().equals("admin")) {
            reportsButton.setVisible(false);
        }
    }

    @FXML
    public void onDealersButtonClicked(ActionEvent event) {
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Dealers", "dealer-form.fxml");
    }

    @FXML
    public void onShoppingButtonClicked(ActionEvent event) {
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Shop", "shop-form.fxml");
    }

    @FXML
    public void onAccountButtonClicked(ActionEvent event) {
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Account", "account-form.fxml");
    }

    public void onReportsButtonClicked(ActionEvent event) {
//        System.out.println("you");
    }
}
