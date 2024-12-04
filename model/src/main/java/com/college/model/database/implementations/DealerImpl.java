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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class DealerImpl implements DealerDAO {
    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_dealers WHERE d_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_dealers";
    private static final String INSERT_QUERY = "INSERT INTO au_dealers (d_name, d_address, d_telephone, d_fax)\n"
                                                + "VALUES(?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_dealers\n" +
                                                "SET d_name = ?, d_address = ?, d_telephone = ?, d_fax = ?\n" +
                                                "WHERE d_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_dealers WHERE d_id = ?";
    
    @Override
    public Dealer getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY);
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
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
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
    public Dealer save(Dealer t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, t.getName());
            statement.setString(2, t.getAddress());
            statement.setString(3, t.getTelephone());
            statement.setString(4, t.getFax());

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
    public boolean update(Dealer t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);

            statement.setString(1, t.getName());
            statement.setString(2, t.getAddress());
            statement.setString(3, t.getTelephone());
            statement.setString(4, t.getFax());
            statement.setInt(5, t.getId());

            int result = statement.executeUpdate();

            return result != 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean delete(Dealer t) {
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
