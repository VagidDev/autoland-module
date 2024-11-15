/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.implementations;

import com.college.model.Automobile;
import com.college.model.database.Database;
import com.college.model.database.interfaces.AutomobileDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vagid Zibliuc
 */
public class AutomobileImpl implements AutomobileDAO {

    @Override
    public Map<Integer, String> getBodyTypes() {
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            Map<Integer, String> bodyTypes = new HashMap<>();
            String query = "SELECT * FROM au_body_type";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                bodyTypes.put(result.getInt(1), result.getString(2));
            }
            return bodyTypes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Automobile getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM AutomobileWithBodyType WHERE a_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Automobile automobile = new Automobile();
                automobile.setId(result.getInt("a_id"));
                automobile.setMark(result.getString("a_mark"));
                automobile.setModel(result.getString("a_model"));
                automobile.setBodyType(result.getString("bt_name"));
                automobile.setPlaceCount(result.getInt("a_place_count"));
                automobile.setProdYear(result.getInt("a_prod_year"));
                return automobile;
            }
            throw new RuntimeException("Automobile not found!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Automobile> getAll() {
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            List<Automobile> automobiles = new ArrayList();
            String query = "SELECT * FROM AutomobileWithBodyType";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Automobile automobile = new Automobile();
                automobile.setId(result.getInt("a_id"));
                automobile.setMark(result.getString("a_mark"));
                automobile.setModel(result.getString("a_model"));
                automobile.setBodyType(result.getString("bt_name"));
                automobile.setPlaceCount(result.getInt("a_place_count"));
                automobile.setProdYear(result.getInt("a_prod_year"));
                automobiles.add(automobile);
            }
            return automobiles;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean save(Automobile t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Automobile t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Automobile t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
