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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class WarrantyImpl implements WarrantyDAO {
    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_warranty WHERE w_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_warranty";
    private static final String INSERT_QUERY = "INSERT INTO au_warranty (w_name, w_duartion, w_price) "
                                              + "VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_warranty\n" +
                                                "SET w_name = ?, w_duartion = ?, w_price = ?\n" +
                                                "WHERE w_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_warranty WHERE w_id = ?";
    
    @Override
    public Warranty getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            Warranty warranty = new Warranty();
            PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY);
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
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
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
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, t.getName());
            statement.setInt(2, t.getDuration());
            statement.setDouble(3, t.getPrice());

            statement.execute();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                t.setId(keys.getInt(1));
                return t;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean update(Warranty t) {
        try (Connection conn = Database.getConnection()) {            
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            
            statement.setString(1, t.getName());
            statement.setInt(2, t.getDuration());
            statement.setDouble(3, t.getPrice());
            statement.setInt(4, t.getId());

            int result = statement.executeUpdate();

            return result != 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean delete(Warranty t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, t.getId());
            statement.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean deleteByID(Integer id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex) {
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
