package com.college.view.controllers;

import com.college.model.entity.Dealer;
import com.college.view.core.AlertHelper;
import com.college.view.core.ContractBuilder;
import com.college.view.core.ControllerManager;
import com.college.view.core.StageService;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.List;


public class DealerController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView headerImage;
    @FXML
    private HBox dealerList;
    @FXML
    private Button backButton;
    @FXML
    private Button confirmButton;

    private com.college.controller.DealerController dealerController;
    private int selectedDealerId = 0;

    @FXML
    public void initialize() {
        if (StageService.getPreviousStage().getTitle().equals("Home")) {
            confirmButton.setDisable(true);
        }

        this.dealerController = ControllerManager.getDealerController();
        List<Dealer> dealers = dealerController.getAllDealers();

        headerImage.fitWidthProperty().bind(rootPane.widthProperty());
        headerImage.fitHeightProperty().bind(rootPane.heightProperty());

        dealerList.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> (double) (dealerList.getChildren().size() * 300), dealerList.getChildren()));

        for (Dealer dealer : dealers) {
            dealerList.getChildren().add(createDealerPane(dealer.getId(), dealer.getName(), dealer.getAddress(), dealer.getTelephone()));
        }

    }

    private Pane createDealerPane(int id, String dealerName, String address, String phone) {
        Pane pane = new Pane();
        pane.setPrefSize(290, 180);
        pane.getStyleClass().add("dealer-pane");
        pane.setId(String.valueOf(id));

        Label nameLabel = new Label(dealerName);
        nameLabel.setLayoutX(14);
        nameLabel.setLayoutY(14);
        nameLabel.setPrefSize(165, 34);
        nameLabel.setFont(Font.font("Lucida Bright Demibold", 18));
        nameLabel.getStyleClass().add("main-text");

        Label addressLabel = new Label(address);
        addressLabel.setLayoutX(14);
        addressLabel.setLayoutY(56);
        addressLabel.setPrefSize(165, 34);
        addressLabel.setFont(Font.font("Lucida Bright Demibold", 16));
        addressLabel.getStyleClass().add("main-text");

        Label phoneLabel = new Label(phone);
        phoneLabel.setLayoutX(14);
        phoneLabel.setLayoutY(101);
        phoneLabel.setPrefSize(165, 34);
        phoneLabel.setFont(Font.font("Lucida Bright Demibold", 14));
        phoneLabel.getStyleClass().add("telephone-text");

        pane.getChildren().addAll(nameLabel, addressLabel, phoneLabel);

        pane.setOnMouseClicked(this::onDealerPaneClicked);

        return pane;
    }

    private void onDealerPaneClicked(MouseEvent mouseEvent) {
        Object clickedObject = mouseEvent.getSource();
        if (clickedObject instanceof Pane clikedPane) {
            this.selectedDealerId = Integer.parseInt(clikedPane.getId());

            dealerList.getChildren().stream()
                    .filter(node -> node.getStyleClass().getLast().equals("clicked-dealer-pane"))
                    .forEach(node -> node.getStyleClass().remove("clicked-dealer-pane"));

            clikedPane.getStyleClass().add("clicked-dealer-pane");
        }
    }


    @FXML
    public void onClickBackButton(ActionEvent event) {
        StageService.closeStageAndOpenPrevious();
    }

    public void onConfirmButton(ActionEvent event) {
        if (selectedDealerId == 0) {
            AlertHelper.emptySelectionAlert();
            return;
        }
        ContractBuilder.setDealerById(selectedDealerId);
        StageService.closeAndSaveStage();
        StageService.buildAndShowStage("Confirmation", "confirmation-form.fxml");
    }

}
