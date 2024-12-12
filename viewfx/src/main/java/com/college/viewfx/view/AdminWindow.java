/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.viewfx.view;

/**
 * @author Vagid Zibliuc
 */

import com.college.controller.*;
import com.college.controller.manager.ControllerManager;
import com.college.model.*;
import com.college.viewfx.view.utils.Tables;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.MenuButtonSkin;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;

import java.util.List;

public class AdminWindow {
    private Stage primaryStage;
    private SplitPane splitPane;
    private final UserController userController;
    private final DealerController dealerController;
    private final WarrantyController warrantyController;
    private final AutomobileController automobileController;
    private final EquipmentController equipmentController;
    private final ContractController contractController;
    //private Map<String, List<String>> classFields;

    public AdminWindow() {
        userController = ControllerManager.getUserController();
        dealerController = ControllerManager.getDealerController();
        warrantyController = ControllerManager.getWarrantyContlroller();
        automobileController = ControllerManager.getAutomobileController();
        equipmentController = ControllerManager.getEquipmentController();
        contractController = ControllerManager.getContractController();
    }

    public void show() {
        primaryStage = new Stage();
        primaryStage.setTitle("Управление автомобилями");

        // Создаем главный контейнер
        BorderPane root = new BorderPane();

        // Создаем меню бар
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        // Создаем SplitPane для разделения кнопок и таблицы
        splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);

        // Создаем панель с кнопками
        VBox buttonPanel = createButtonPanel();

        // Создаем таблицу
        TableView<User> table = createUserTable();

        // Добавляем панель с кнопками и таблицу в SplitPane
        splitPane.getItems().addAll(buttonPanel, table);
        splitPane.setDividerPositions(0.2); // Устанавливаем соотношение 30/70

        root.setCenter(splitPane);

        Scene scene = new Scene(root, 1300, 700); // Устанавливаем размер окна
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("Файл");
        Menu tableMenu = new Menu("Таблицы");

        MenuItem userItem = new MenuItem("Пользователи");
        userItem.setOnAction(e -> {
            loadTable(Tables.USER);
        });
        MenuItem dealerItem = new MenuItem("Оф. дилеры");
        dealerItem.setOnAction(actionEvent -> {
            loadTable(Tables.DEALER);
        });
        MenuItem warrantyItem = new MenuItem("Гарантии");
        warrantyItem.setOnAction(actionEvent -> {
            loadTable(Tables.WARRANTY);
        });
        MenuItem automobileItem = new MenuItem("Автомобили");
        automobileItem.setOnAction(actionEvent -> {
            loadTable(Tables.AUTOMOBILE);
        });

        tableMenu.getItems().addAll(userItem, dealerItem, warrantyItem, automobileItem);


        menuBar.getMenus().addAll(fileMenu, tableMenu);

        return menuBar;
    }

    private VBox createButtonPanel() {
        VBox buttonPanel = new VBox(10);
        buttonPanel.setPadding(new Insets(10));

        Button addButton = new Button("Добавить");
        Button updateButton = new Button("Обновить");
        Button deleteButton = new Button("Удалить");

        // Устанавливаем максимальную ширину для кнопок
        addButton.setMaxWidth(Double.MAX_VALUE);
        updateButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);

        buttonPanel.getChildren().addAll(addButton, updateButton, deleteButton);
        return buttonPanel;
    }

    private void loadTable(Tables table) {
        TableView tableView = null;
        switch (table) {
            case DEALER:
                tableView = createDealerTable();
                break;
            case AUTOMOBILE:
                break;
            case CONTRACT:
                break;
            case EQUIPMENT:
                break;
            case WARRANTY:
                tableView = createWarrantyTable();
                break;
            case USER:
            default:
                tableView = createUserTable();
                break;
        }


        splitPane.getItems().set(1, tableView);
    }

    private TableView<User> createUserTable() {
        TableView<User> table = new TableView<>();
        table.getItems().clear();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<User, String> loginColumn = new TableColumn<>("Логин");
        loginColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLogin()));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Пароль");
        passwordColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));

        TableColumn<User, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<User, String> surnameColumn = new TableColumn<>("Фамилия");
        surnameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSurname()));

        TableColumn<User, String> birthdayColumn = new TableColumn<>("День рождения");
        birthdayColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirthday().toString()));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<User, String> telephoneColumn = new TableColumn<>("Телефон");
        telephoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));

        TableColumn<User, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));

        table.getColumns().addAll(loginColumn, passwordColumn, nameColumn, surnameColumn, birthdayColumn, emailColumn, telephoneColumn, addressColumn);

        List<User> users = userController.getUsers();

        ObservableList<User> data = FXCollections.observableArrayList(users);

        table.setItems(data);

        return table;
    }

    private TableView<Dealer> createDealerTable() {
        TableView<Dealer> table = new TableView<>();
        table.getItems().clear();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Dealer, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Dealer, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));

        TableColumn<Dealer, String> telephoneColumn = new TableColumn<>("Телефон");
        telephoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));

        TableColumn<Dealer, String> faxColumn = new TableColumn<>("Телефон ФАКС");
        faxColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFax()));

        table.getColumns().addAll(nameColumn, addressColumn, telephoneColumn, faxColumn);

        List<Dealer> dealers = dealerController.getAllDealers();

        ObservableList<Dealer> data = FXCollections.observableArrayList(dealers);

        table.setItems(data);

        return table;
    }

    private TableView<Warranty> createWarrantyTable() {
        TableView<Warranty> table = new TableView<>();
        table.getItems().clear();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Warranty, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Warranty, Number> durationColumn = new TableColumn<>("Продолжительность (месяц)");
        durationColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getDuration()));

        TableColumn<Warranty, Number> priceColumn = new TableColumn<>("Цена");
        priceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrice()));

        table.getColumns().addAll(nameColumn, durationColumn, priceColumn);

        List<Warranty> warranties = warrantyController.getAllWarranties();

        ObservableList<Warranty> data = FXCollections.observableArrayList(warranties);

        table.setItems(data);

        return table;
    }


    private TableView<Automobile> createAutomobileTable() {
        TableView<Automobile> table = new TableView<>();
        table.getItems().clear();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Automobile, String> markColumn = new TableColumn<>("Марка");
        markColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMark()));

        TableColumn<Automobile, String> modelColumn = new TableColumn<>("Модель");
        modelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getModel()));

        TableColumn<Automobile, String> bodyColumn = new TableColumn<>("Тип кузова");
        bodyColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBodyType()));

        TableColumn<Automobile, Number> placeColumn = new TableColumn<>("Количество мест");
        placeColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPlaceCount()));

        TableColumn<Automobile, Number> yearColumn = new TableColumn<>("Год производства");
        yearColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getProdYear()));

        TableColumn<Automobile, String> sourceColumn = new TableColumn<>("Путь к картинке");
        sourceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getImagePath()));

        table.getColumns().addAll(markColumn, modelColumn, bodyColumn, placeColumn, yearColumn, sourceColumn);

        List<Automobile> automobiles = automobileController.getAllAutomobiles();

        ObservableList<Automobile> data = FXCollections.observableArrayList(automobiles);

        table.setItems(data);

        return table;
    }
}
