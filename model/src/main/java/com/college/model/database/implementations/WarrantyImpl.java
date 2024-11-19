/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.implementations;

import com.college.model.Warranty;
import com.college.model.database.Database;
import com.college.model.database.interfaces.WarrantyDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class WarrantyImpl implements WarrantyDAO {

    @Override
    public Warranty getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            Warranty warranty = new Warranty();
            String query = "SELECT * FROM au_warranty WHERE w_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                warranty.setId(result.getInt("w_id"));
                warranty.setName(result.getString("w_name"));
                warranty.setDuration(result.getInt("w_duartion"));
                warranty.setPrice(result.getDouble("w_price"));
                return warranty;
            }
            throw new RuntimeException("Warranty not found!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Warranty> getAll() {
        List<Warranty> warranties = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM au_warranty";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Warranty warranty = new Warranty();
                warranty.setId(result.getInt("w_id"));
                warranty.setName(result.getString("w_name"));
                warranty.setDuration(result.getInt("w_duartion"));
                warranty.setPrice(result.getDouble("w_price"));
                warranties.add(warranty);
            }
            return warranties;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Warranty save(Warranty t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Warranty t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Warranty t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
