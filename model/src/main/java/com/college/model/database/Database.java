/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vagid Zibliuc
 */
public final class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/autoland_db";
    private static final String USER = "autoadmin";
    private static final String PASSWORD = "autoland";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private Database() {
    }
}
