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
    private static final String GET_BY_ID_QUERY = "SELECT * FROM AutomobileWithBodyType WHERE a_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM AutomobileWithBodyType";
    private static final String INSERT_QUERY = "INSERT INTO au_automobiles (a_mark, a_model, a_body_id, a_place_count, a_prod_year, a_image) "
                                                + "VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_automobiles\n" +
                                                "SET a_mark = ?, a_model = ?, a_body_id = ?, a_place_count = ?, a_prod_year = ?\n" +
                                                "WHERE a_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_automobiles WHERE a_id = ?";
    
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
            PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY);
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
                automobile.setImagePath(result.getString("a_image"));
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
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
            while (result.next()) {
                Automobile automobile = new Automobile();
                automobile.setId(result.getInt("a_id"));
                automobile.setMark(result.getString("a_mark"));
                automobile.setModel(result.getString("a_model"));
                automobile.setBodyType(result.getString("bt_name"));
                automobile.setPlaceCount(result.getInt("a_place_count"));
                automobile.setProdYear(result.getInt("a_prod_year"));
                automobile.setImagePath(result.getString("a_image"));
                automobiles.add(automobile);
            }
            return automobiles;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private int getIdOfBodyType(String name) {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT bt_id FROM au_body_type WHERE bt_name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public Automobile save(Automobile t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getMark());
            statement.setString(2, t.getModel());
            statement.setInt(3, getIdOfBodyType(t.getBodyType()));
            statement.setInt(4, t.getPlaceCount());
            statement.setInt(5, t.getProdYear());
            statement.setString(6, t.getImagePath());
            
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
    public boolean update(Automobile t) {
        try (Connection conn = Database.getConnection()) {
            
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, t.getMark());
            statement.setString(2, t.getModel());
            statement.setInt(3, getIdOfBodyType(t.getBodyType()));
            statement.setInt(4, t.getPlaceCount());
            statement.setInt(5, t.getProdYear());
            statement.setString(6, t.getImagePath());
            statement.setInt(7, t.getId());
            
            int result = statement.executeUpdate();
            
            return result != 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Automobile t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, t.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(Integer id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
