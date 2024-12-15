package com.college.model.database.implementations;

import com.college.model.Automobile;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.AutomobileDAO;
import com.college.model.database.interfaces.BodyTypeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vagid Zibliuc
 */

public class AutomobileImpl implements AutomobileDAO {
    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_automobiles WHERE a_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_automobiles";
    private static final String INSERT_QUERY = "INSERT INTO au_automobiles (a_mark, a_model, a_body_id, a_place_count, a_prod_year) "
            + "VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_automobiles\n" +
            "SET a_mark = ?, a_model = ?, a_body_id = ?, a_place_count = ?, a_prod_year = ?\n" +
            "WHERE a_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_automobiles WHERE a_id = ?";

    private BodyTypeDAO bodyTypeDAO;

    public AutomobileImpl(BodyTypeDAO bodyTypeDAO) {
        this.bodyTypeDAO = bodyTypeDAO;
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
                automobile.setBodyType(bodyTypeDAO.getById(result.getInt("a_body_id")));
                automobile.setPlaceCount(result.getInt("a_place_count"));
                automobile.setProdYear(result.getInt("a_prod_year"));
                return automobile;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Automobile> getAll() {
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            List<Automobile> automobiles = new ArrayList<>();
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
            while (result.next()) {
                Automobile automobile = new Automobile();
                automobile.setId(result.getInt("a_id"));
                automobile.setMark(result.getString("a_mark"));
                automobile.setModel(result.getString("a_model"));
                automobile.setBodyType(bodyTypeDAO.getById(result.getInt("a_body_id")));
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
    public Automobile save(Automobile t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, t.getMark());
            statement.setString(2, t.getModel());
            statement.setInt(3, t.getBodyType().getId());
            statement.setInt(4, t.getPlaceCount());
            statement.setInt(5, t.getProdYear());

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
    public void update(Automobile t) {
        try (Connection conn = Database.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, t.getMark());
            statement.setString(2, t.getModel());
            statement.setInt(3, t.getBodyType().getId());
            statement.setInt(4, t.getPlaceCount());
            statement.setInt(5, t.getProdYear());
            statement.setInt(6, t.getId());

            int result = statement.executeUpdate();

            if (result == 0) {
                throw new EntityNotFoundException("Automobile with id=" + t.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Automobile t) throws CascadeDependencyException {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, t.getId());
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("Automobile with id=" + t.getId() + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(Integer id) throws CascadeDependencyException {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("Automobile with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
