/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.viewfx.view;

/**
 *
 * @author Vagid Zibliuc
 */
//import com.college.controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;

public class AdminWindow {
//    private UserController userController;

//    public AdminWindow() {
//        userController = new UserController();
//    }


    public void show() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Управление автомобилями");

        // Создаем главный контейнер
        BorderPane root = new BorderPane();

        // Создаем меню бар
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        // Создаем SplitPane для разделения кнопок и таблицы
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);

        // Создаем панель с кнопками
        VBox buttonPanel = createButtonPanel();
        
        // Создаем таблицу
        TableView<Car> table = createTable();

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
        
        // Создаем 6 меню
        for (int i = 1; i <= 6; i++) {
            Menu menu = new Menu("Меню " + i);
            menuBar.getMenus().add(menu);
        }
        
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

    private TableView<Car> createTable() {
        TableView<Car> table = new TableView<>();

        // Создаем колонки таблицы
        // В реальном приложении эти данные должны загружаться из базы данных
        TableColumn<Car, String> brandColumn = new TableColumn<>("Марка");
        TableColumn<Car, String> modelColumn = new TableColumn<>("Модель");
        TableColumn<Car, String> yearColumn = new TableColumn<>("Год выпуска");

        table.getColumns().addAll(brandColumn, modelColumn, yearColumn);

        return table;
    }

    // Вспомогательный класс для демонстрации таблицы
    public static class Car {
        private String brand;
        private String model;
        private String year;

        public Car(String brand, String model, String year) {
            this.brand = brand;
            this.model = model;
            this.year = year;
        }

        // Геттеры и сеттеры
    }
}
