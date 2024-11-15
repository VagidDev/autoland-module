/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.implementations;

import com.college.model.Dealer;
import com.college.model.database.Database;
import com.college.model.database.interfaces.DealerDAO;
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
public class DealerImpl implements DealerDAO {

    @Override
    public Dealer getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM au_dealers WHERE d_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Dealer dealer = new Dealer();
                dealer.setId(result.getInt("d_id"));
                dealer.setName(result.getString("d_name"));
                dealer.setAddress(result.getString("d_address"));
                dealer.setTelephone(result.getString("d_telephone"));
                dealer.setFax(result.getString("d_fax"));
                return dealer;
            }
            throw new RuntimeException("Dealer not found!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Dealer> getAll() {
        List<Dealer> dealers = new ArrayList();
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM au_dealers";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Dealer dealer = new Dealer();
                dealer.setId(result.getInt("d_id"));
                dealer.setName(result.getString("d_name"));
                dealer.setAddress(result.getString("d_address"));
                dealer.setTelephone(result.getString("d_telephone"));
                dealer.setFax(result.getString("d_fax"));
                dealers.add(dealer);
            }
            return dealers;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean save(Dealer t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Dealer t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Dealer t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
