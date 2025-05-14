package com.college.view.controllers;

import com.college.controller.AuthorizationController;
import com.college.controller.ContractController;
import com.college.model.entity.Contract;
import com.college.model.entity.User;
import com.college.view.core.AnimationType;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
import com.college.view.core.StageService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.List;


public class AccountController {
    @FXML
    private ImageView avatar;
    @FXML
    private Label fullName;
    @FXML
    private Label login;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label address;
    @FXML
    private TableView<Contract> contractTable;

    private AuthorizationController authorizationController;
    private ContractController contractController;

    public void createContractTable(List<Contract> contracts) {
        this.contractTable.getColumns().clear();

        TableColumn<Contract, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Contract, String> userColumn = new TableColumn<>("User");
        userColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUser().getName() + " " + cellData.getValue().getUser().getSurname()));

        TableColumn<Contract, String> dealerColumn = new TableColumn<>("Dealer");
        dealerColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDealer().getName()));

        TableColumn<Contract, String> automobileColumn = new TableColumn<>("Automobile");
        automobileColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEquipment().getAutomobile().getMark() + " " + cellData.getValue().getEquipment().getAutomobile().getModel()));


        TableColumn<Contract, String> equipmentColumn = new TableColumn<>("Equipment");
        equipmentColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEquipment().getName()));

        TableColumn<Contract, String> warrantyColumn = new TableColumn<>("Warranty");
        warrantyColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getWarranty().getName()));

        TableColumn<Contract, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getConclusionDate().toString()));

        this.contractTable.getColumns().addAll(idColumn, userColumn, dealerColumn, automobileColumn, equipmentColumn, warrantyColumn, dateColumn);
        ObservableList<Contract> contractList = FXCollections.observableArrayList(contracts);
        this.contractTable.setItems(contractList);
        this.contractTable.refresh();
    }

    public void initialize() {
        contractController = ControllerManager.getContractController();
        authorizationController = ControllerManager.getAuthorizationController();

        User currentUser = authorizationController.getCurrentUser();

        setUserInfo(currentUser);
        createContractTable(contractController.getContractsByUser(currentUser));

        Circle circle = new Circle(100, 100, 100);
        avatar.setClip(circle);

        Platform.runLater(() -> {
            Stage stage = (Stage) avatar.getScene().getWindow();
            //stage.setOnCloseRequest(event -> StageService.buildAndShowStage("Home", "home-form.fxml"));
        });

    }

    private void setUserInfo(User user) {

        fullName.setText(user.getName() + " " + user.getSurname());
        login.setText(user.getLogin());
        phone.setText(user.getTelephone());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
    }

    public void editButtonClicked(ActionEvent actionEvent) {
        //TODO: add logic for transferring data about user account
        SceneRouterService.getSceneRouter().switchTo("account-register-form.fxml", AnimationType.FADE);
        /*Stage currentStage = StageService.getCurrentStage();
        FXMLLoader loader = StageService.loadFXML("account-register-form.fxml");
        Stage tmpStage = StageService.buildStage("Edit Account", loader);
        currentStage.hide();
        tmpStage.showAndWait();
        StageService.unregisterStage(tmpStage);
        currentStage.show();*/
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
