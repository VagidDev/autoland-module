package com.college.view.controllers;

import com.college.controller.AuthorizationController;
import com.college.controller.ContractController;
import com.college.controller.UserController;
import com.college.model.entity.Contract;
import com.college.model.entity.User;
import com.college.view.core.AnimationType;
import com.college.view.core.ControllerManager;
import com.college.view.core.SceneRouterService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;


public class AccountController {
    @FXML private ImageView avatar;
    @FXML private Label fullName;
    @FXML private Label login;
    @FXML private Label phone;
    @FXML private Label email;
    @FXML private Label address;
    @FXML private TableView<Contract> contractTable;

    private boolean avatarChanged;
    private Image originalImage;

    private AuthorizationController authorizationController;
    private UserController userController;
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
        userController =  ControllerManager.getUserController();
        authorizationController = ControllerManager.getAuthorizationController();

        User currentUser = authorizationController.getCurrentUser();

        setUserInfo(currentUser);
        createContractTable(contractController.getContractsByUser(currentUser));

        Circle circle = new Circle(100, 100, 100);
        avatar.setClip(circle);
        setImage(currentUser.getAvatar());


    }

    public void chooseUserAvatar(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png")
        );

        File selectedFile = fileChooser.showOpenDialog(SceneRouterService.getSceneRouter().getCurrentStage()); // передай Stage текущего окна

        if (selectedFile != null) {
            avatarChanged = true;
            setImage(selectedFile.toURI().toString());
        }
    }

    private void setImage(String url) {
        originalImage = new Image(url);

        PixelReader reader = originalImage.getPixelReader();

        int height = (int) originalImage.getHeight();
        int width = (int) originalImage.getWidth();

        if (height == width) {
            avatar.setImage(originalImage);
            return;
        }

        WritableImage croppedImage = null;

        if (height > width) {
            int cropY = (height - width) / 2;
            croppedImage = new WritableImage(reader, 0, cropY, width, width);
        } else {
            int cropX = (width - height) / 2;
            croppedImage = new WritableImage(reader, cropX, 0, height, height);
        }

        avatar.setImage(croppedImage);
    }

    private void setUserInfo(User user) {

        fullName.setText(user.getName() + " " + user.getSurname());
        login.setText(user.getLogin());
        phone.setText(user.getTelephone());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
    }

    public void changePasswordButtonClicked(ActionEvent actionEvent) {
        SceneRouterService.getSceneRouter().switchTo("registration-form.fxml", AnimationType.ZOOM);
    }

    public void editButtonClicked(ActionEvent actionEvent) {
        SceneRouterService.getSceneRouter().switchTo("account-register-form.fxml", AnimationType.FADE);
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        if (avatarChanged) {
            User currentUser = authorizationController.getCurrentUser();
            currentUser.setAvatar(originalImage.getUrl());
            userController.editUser(currentUser);
        }
        SceneRouterService.getSceneRouter().switchToPreviousScene();
    }
}
