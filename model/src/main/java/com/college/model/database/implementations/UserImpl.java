/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.implementations;

import com.college.model.User;
import com.college.model.database.Database;
import com.college.model.database.interfaces.UserDAO;
import java.sql.Connection;
import java.sql.Date;
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
public class UserImpl implements UserDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_users WHERE u_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_users";
    private static final String INSERT_QUERY = "INSERT INTO au_users (u_login, u_password, u_name, u_surname, u_birthday, u_email, u_telephone, u_address) "
                                                + "VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_users\n"
                                                + "SET u_login = ?, u_password = ?, u_name = ?, u_surname = ?, u_birthday = ?, u_email = ?, u_telephone = ?, u_address = ?\n"
                                                + "WHERE u_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_users WHERE u_id = ?;";
    private static final String SEARCH_BY_LOGIN_AND_PASSWORD = "SELECT * FROM au_users WHERE u_login = ? AND u_password = ?";
    
    @Override
    public User getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                User user = new User();
                user.setId(result.getInt("u_id"));
                user.setLogin(result.getString("u_login"));
                user.setPassword(result.getString("u_password"));
                user.setName(result.getString("u_name"));
                user.setSurname(result.getString("u_surname"));
                user.setBirthday(result.getDate("u_birthday"));
                user.setEmail(result.getString("u_email"));
                user.setTelephone(result.getString("u_telephone"));
                user.setAddress(result.getString("u_address"));
                return user;
            }
            throw new RuntimeException("User not found!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection conn = Database.getConnection()) {
            List<User> users = new ArrayList<>();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("u_id"));
                user.setLogin(result.getString("u_login"));
                user.setPassword(result.getString("u_password"));
                user.setName(result.getString("u_name"));
                user.setSurname(result.getString("u_surname"));
                user.setBirthday(result.getDate("u_birthday"));
                user.setEmail(result.getString("u_email"));
                user.setTelephone(result.getString("u_telephone"));
                user.setAddress(result.getString("u_address"));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User save(User t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, t.getLogin());
            statement.setString(2, t.getPassword());
            statement.setString(3, t.getName());
            statement.setString(4, t.getSurname());
            statement.setDate(5, new Date((t.getBirthday().getTime())));
            statement.setString(6, t.getEmail());
            statement.setString(7, t.getTelephone());
            statement.setString(8, t.getAddress());

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
    public boolean update(User t) {
        try (Connection conn = Database.getConnection()) {
            
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);

            statement.setString(1, t.getLogin());
            statement.setString(2, t.getPassword());
            statement.setString(3, t.getName());
            statement.setString(4, t.getSurname());
            statement.setDate(5, new Date((t.getBirthday().getTime())));
            statement.setString(6, t.getEmail());
            statement.setString(7, t.getTelephone());
            statement.setString(8, t.getAddress());
            statement.setInt(9, t.getId());

            int result = statement.executeUpdate();

            return result != 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean delete(User t) {
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

    @Override
    public User getByLoginAndPassword(String login, String password) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SEARCH_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                User user = new User();
                user.setId(result.getInt("u_id"));
                user.setLogin(result.getString("u_login"));
                user.setPassword(result.getString("u_password"));
                user.setName(result.getString("u_name"));
                user.setSurname(result.getString("u_surname"));
                user.setBirthday(result.getDate("u_birthday"));
                user.setEmail(result.getString("u_email"));
                user.setTelephone(result.getString("u_telephone"));
                user.setAddress(result.getString("u_address"));
                return user;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
