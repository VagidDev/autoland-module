package com.college.view.controllers;

import com.college.view.core.AnimationType;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
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
    private Button adminButton;

    @FXML
    private void initialize() {
        if (!ControllerManager.getAuthorizationController().getCurrentUser().getRole().equals("admin")) {
            adminButton.setVisible(false);
        }
    }

    @FXML
    public void onDealersButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchTo("dealer-form.fxml", AnimationType.ZOOM);
    }

    @FXML
    public void onShoppingButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchTo("shop-form.fxml", AnimationType.ZOOM); //temporary, after test turn animation to slide
    }

    @FXML
    public void onAccountButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchTo("account-form.fxml", AnimationType.ZOOM);
    }

    public void onLogoutButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().clearStack();
        ControllerManager.getAuthorizationController().logout();
        SceneRouterService.getSceneRouter().switchTo("authorization-form.fxml", AnimationType.ZOOM);
    }

    public void onAdminPanelButtonClicked(ActionEvent event) {
        SceneRouterService.getSceneRouter().switchTo("admin-form.fxml", AnimationType.ZOOM);
    }
}
