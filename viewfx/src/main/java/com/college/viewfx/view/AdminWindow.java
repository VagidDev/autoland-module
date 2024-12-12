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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminWindow {
    private SplitPane splitPane;
    private TableView tableView;
    private Button addEquipButton;
    private final UserController userController;
    private final DealerController dealerController;
    private final WarrantyController warrantyController;
    private final AutomobileController automobileController;
    private final EquipmentController equipmentController;
    private final ContractController contractController;

    public AdminWindow() {
        userController = ControllerManager.getUserController();
        dealerController = ControllerManager.getDealerController();
        warrantyController = ControllerManager.getWarrantyContlroller();
        automobileController = ControllerManager.getAutomobileController();
        equipmentController = ControllerManager.getEquipmentController();
        contractController = ControllerManager.getContractController();
    }

    public void show() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Управление автомобилями");

        BorderPane root = new BorderPane();

        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);

        VBox buttonPanel = createButtonPanel();

        tableView = createUserTable();

        splitPane.getItems().addAll(buttonPanel, tableView);
        splitPane.setDividerPositions(0.2);

        root.setCenter(splitPane);

        Scene scene = new Scene(root, 1300, 700);
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
        MenuItem equipItem = new MenuItem("Комплектации");
        equipItem.setOnAction(actionEvent -> {
            loadTable(Tables.EQUIPMENT);
        });
        MenuItem contractItem = new MenuItem("Контракт");
        contractItem.setOnAction(actionEvent -> {
            loadTable(Tables.CONTRACT);
        });

        tableMenu.getItems().addAll(userItem, dealerItem, warrantyItem, automobileItem, equipItem, contractItem);


        menuBar.getMenus().addAll(fileMenu, tableMenu);

        return menuBar;
    }

    private VBox createButtonPanel() {
        VBox buttonPanel = new VBox(10);
        buttonPanel.setPadding(new Insets(10));

        Button addButton = new Button("Добавить");
        addButton.setOnAction(actionEvent -> {
            addValue();
        });
        Button updateButton = new Button("Редактировать");
        updateButton.setOnAction(actionEvent -> {
            updateValue();
        });
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(actionEvent -> {
            deleteValue();
        });
        addEquipButton = new Button("Добавить комплектацию");
        addEquipButton.setVisible(false);
        addEquipButton.setOnAction(actionEvent -> {
            addEquipValue();
        });

        addButton.setMaxWidth(Double.MAX_VALUE);
        updateButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        addEquipButton.setMaxWidth(Double.MAX_VALUE);

        buttonPanel.getChildren().addAll(addButton, updateButton, deleteButton, addEquipButton);
        return buttonPanel;
    }

    private void loadTable(Tables table) {
        switch (table) {
            case DEALER:
                tableView = createDealerTable();
                break;
            case AUTOMOBILE:
                tableView = createAutomobileTable();
                break;
            case CONTRACT:
                tableView = createContractTable();
                break;
            case EQUIPMENT:
                tableView = createEquipmentTable();
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
        checkEquipButton(table);
    }

    private void checkEquipButton(Tables table) {
        if (table == Tables.AUTOMOBILE) {
            addEquipButton.setVisible(true);
        } else {
            addEquipButton.setVisible(false);
        }
    }

    private TableView<User> createUserTable() {
        TableView<User> table = new TableView<>();
        table.getItems().clear();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<User, String> loginColumn = new TableColumn<>("Логин");
        loginColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLogin()));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Пароль");
        passwordColumn.setCellValueFactory(data -> new SimpleStringProperty("********"));

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

    private TableView<Equipment> createEquipmentTable() {
        TableView<Equipment> table = new TableView<>();
        table.getItems().clear();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Equipment, String> autoColumn = new TableColumn<>("Автомобиль");
        autoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutomobile().getMark() + " " + data.getValue().getAutomobile().getModel()));

        TableColumn<Equipment, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Equipment, String> engNameColumn = new TableColumn<>("Название двигателя");
        engNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEngineName()));

        TableColumn<Equipment, String> engTypeColumn = new TableColumn<>("Тип двигателя");
        engTypeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEngineType()));

        TableColumn<Equipment, String> engVolColumn = new TableColumn<>("Объем двигателя");
        engVolColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getEngineVolume())));

        TableColumn<Equipment, Number> horseColumn = new TableColumn<>("Лошадиные силы");
        horseColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getHorsepower()));

        TableColumn<Equipment, String> suspensionColumn = new TableColumn<>("Тип подвески");
        suspensionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSuspensiveType()));

        TableColumn<Equipment, String> driveColumn = new TableColumn<>("Привод");
        driveColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDriveType()));

        TableColumn<Equipment, String> gearColumn = new TableColumn<>("КПП");
        gearColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGearboxType()));

        TableColumn<Equipment, Number> countColumn = new TableColumn<>("Количество скоростей");
        countColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getSpeedCount()));

        TableColumn<Equipment, String> fuelColumn = new TableColumn<>("Тип топлива");
        fuelColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFuelType()));

        TableColumn<Equipment, String> interiorColumn = new TableColumn<>("Интерьер");
        interiorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getInterior()));

        TableColumn<Equipment, String> bodyColumn = new TableColumn<>("Обвес");
        bodyColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBodyKit()));

        TableColumn<Equipment, Number> weightColumn = new TableColumn<>("Вес");
        weightColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getWeigth()));

        TableColumn<Equipment, Number> priceColumn = new TableColumn<>("Цена");
        priceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrice()));

        table.getColumns().addAll(autoColumn, nameColumn, engNameColumn, engTypeColumn, engVolColumn, horseColumn, suspensionColumn, driveColumn,
                gearColumn, countColumn, fuelColumn, interiorColumn, bodyColumn, weightColumn, priceColumn);

        List<Equipment> equipments = equipmentController.getAllEquipments();

        ObservableList<Equipment> data = FXCollections.observableArrayList(equipments);

        table.setItems(data);

        return table;
    }

    private TableView<Contract> createContractTable() {
        TableView<Contract> table = new TableView<>();
        table.getItems().clear();

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Contract, String> userColumn = new TableColumn<>("Покупатель");
        userColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser().getName() + " " + data.getValue().getUser().getSurname()));

        TableColumn<Contract, String> dealerColumn = new TableColumn<>("Дилер");
        dealerColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDealer().getName()));

        TableColumn<Contract, String> autoColumn = new TableColumn<>("Автомобиль");
        autoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAutomobile().getMark() + " " + data.getValue().getAutomobile().getModel()));

        TableColumn<Contract, String> equipColumn = new TableColumn<>("Комплектация");
        equipColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipment().getName()));

        TableColumn<Contract, String> warrantyColumn = new TableColumn<>("Гарантия");
        warrantyColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWarranty().getName()));

        TableColumn<Contract, String> dataColumn = new TableColumn<>("Дата покупки");
        dataColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getConclusionDate().toString()));

        table.getColumns().addAll(userColumn, dealerColumn, equipColumn, warrantyColumn, dataColumn);

        List<Contract> contracts = contractController.getAllContracts();

        ObservableList<Contract> data = FXCollections.observableArrayList(contracts);

        table.setItems(data);

        return table;
    }

    private void addValue() {
        String form = tableView.getItems().get(0).getClass().getSimpleName();
        switch (form) {
            case "User":
                new UserForm(userController, this::refreshTable).show();
                break;
            case "Dealer":
                new DealerForm(dealerController, this::refreshTable).show();
                break;
            case "Warranty":
                new WarrantyForm(warrantyController, this::refreshTable).show();
                break;
            case "Automobile":
                new AutomobileForm(automobileController, this::refreshTable).show();
                break;
            default:
                new Alert(Alert.AlertType.WARNING, "В данной таблице нельзя добавлять информацию", ButtonType.APPLY).showAndWait();
                break;
        }
    }

    private void addEquipValue() {
        Automobile automobile = (Automobile) getSelectedItem();
        if (automobile == null) {
            new Alert(Alert.AlertType.WARNING, "Выберите автомобиль!", ButtonType.OK).show();
            return;
        }
        new EquipmentForm(automobile, equipmentController, this::refreshTable).show();
        loadTable(Tables.EQUIPMENT);
    }

    private void updateValue() {
        Object obj = getSelectedItem();
        if (obj == null) {
            new Alert(Alert.AlertType.WARNING, "Выберите строку!", ButtonType.APPLY).showAndWait();
            return;
        }
        String form = obj.getClass().getSimpleName();
        switch (form) {
            case "Dealer":
                new DealerForm((Dealer) obj, dealerController, this::refreshTable).show();
                break;
            case "Warranty":
                new WarrantyForm((Warranty) obj, warrantyController, this::refreshTable).show();
                break;
            case "Automobile":
                new AutomobileForm((Automobile) obj, automobileController, this::refreshTable).show();
                break;
            case "Equipment":
                new EquipmentForm((Equipment) obj, equipmentController, this::refreshTable).show();
                break;
            default:
                new Alert(Alert.AlertType.WARNING, "В данной таблице нельзя изменять информацию", ButtonType.APPLY).showAndWait();
                break;
        }
    }

    private void deleteValue() {
        Object obj = getSelectedItem();
        String form = obj.getClass().getSimpleName();
        var alert = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверенны, что хотите удалить данную строку?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (ButtonType.NO == alert.getResult())
            return;

        boolean isProcessed = false;

        switch (form) {
            case "Dealer":
                isProcessed = dealerController.deleteDealer(((Dealer) obj).getId());
                break;
            case "User":
                isProcessed = userController.deleteUserById(((User) obj).getId());
                break;
            case "Warranty":
                isProcessed = warrantyController.deleteWarranty(((Warranty) obj).getId());
                break;
            case "Automobile":
                isProcessed = automobileController.deleteAutomobile(((Automobile) obj).getId());
                break;
            case "Equipment":
                Equipment equipment = ((Equipment) obj);
                isProcessed = equipmentController.deleteEquipment(equipment.getAutomobile().getId(), equipment.getId());
                break;
        }
        if (isProcessed) {
            new Alert(Alert.AlertType.INFORMATION, "Строка удалена", ButtonType.APPLY).showAndWait();
            refreshTable();
        } else {
            new Alert(Alert.AlertType.ERROR, "Нельзя удалить данную запись так как она используется в другой таблице!", ButtonType.APPLY).showAndWait();
        }

    }

    public void refreshTable() {
        try {
            String form = tableView.getItems().get(0).getClass().getSimpleName();
            List list = null;
            switch (form) {
                case "User":
                    list = userController.getUsers();
                    break;
                case "Dealer":
                    list = dealerController.getAllDealers();
                    break;
                case "Warranty":
                    list = warrantyController.getAllWarranties();
                    break;
                case "Automobile":
                    list = automobileController.getAllAutomobiles();
                    break;
                case "Equipment":
                    list = equipmentController.getAllEquipments();
                    break;
                case "Contract":
                    list = contractController.getAllContracts();
                    break;
                default:
                    list = new ArrayList();
                    break;
            }
            tableView.getItems().setAll(list);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    private Object getSelectedItem() {
        return tableView.getSelectionModel().getSelectedItem();
    }

}
