package com.college.view.controllers;

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

import java.io.IOException;

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
    public void initialize() {
        headerImage.fitWidthProperty().bind(rootPane.widthProperty());
        headerImage.fitHeightProperty().bind(rootPane.heightProperty());

        dealerList.prefWidthProperty().bind(Bindings.createDoubleBinding(() -> (double) (dealerList.getChildren().size() * 300), dealerList.getChildren()));

        dealerList.getChildren().add(createDealerPane("name", "address", "+3736582"));
        dealerList.getChildren().add(createDealerPane("name", "address", "+3736582"));
        dealerList.getChildren().add(createDealerPane("name", "address", "+3736582"));
        dealerList.getChildren().add(createDealerPane("name", "address", "+3736582"));

    }

    private Pane createDealerPane(String name, String address, String phoneNumber) {
        Pane pane = new Pane();
        pane.setPrefSize(290, 180);
        pane.getStyleClass().add("list-element");

        Label nameLabel = new Label(name);
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

        Label phoneLabel = new Label(phoneNumber);
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
            dealerList.getChildren().stream()
                    .filter(node -> node.getStyleClass().getLast().equals("clicked-list-element"))
                    .forEach(node -> node.getStyleClass().remove("clicked-list-element"));

            clikedPane.getStyleClass().add("clicked-list-element");
        }
    }


    @FXML
    public void onClickBackButton(ActionEvent event) throws IOException {
        StageService.closeCurrentStage(event);
        StageService.buildAndShowStage("Home", "home-form.fxml");
    }

    public void onConfirmButton(ActionEvent event) throws IOException {
        StageService.buildAndShowStage("Confirm", "confirmation-form.fxml");
        StageService.closeCurrentStage(event);
    }

}
