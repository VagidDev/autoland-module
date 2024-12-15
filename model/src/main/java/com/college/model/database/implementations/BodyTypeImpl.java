package com.college.model.database.implementations;

import com.college.model.BodyType;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.BodyTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BodyTypeImpl implements BodyTypeDAO {
    private static final String SELECT_SQL = "SELECT * FROM au_body_type";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM au_body_type WHERE bt_id = ?";
    private static final String INSERT_VALUES_SQL = "INSERT INTO au_body_type (bt_name) VALUES (?)";
    private static final String UPDATE_VALUES_SQL = "UPDATE au_body_type SET bt_name = ? WHERE bt_id = ?";
    private static final String DELETE_SQL = "DELETE FROM au_body_type WHERE bt_id = ?";

    @Override
    public BodyType getById(Integer integer) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BodyType bodyType = new BodyType();
                bodyType.setId(integer);
                bodyType.setName(resultSet.getString("bt_name"));
                return bodyType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<BodyType> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<BodyType> bodyTypes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_SQL);
            while (result.next()) {
                BodyType bodyType = new BodyType();
                bodyType.setId(result.getInt("bt_id"));
                bodyType.setName(result.getString("bt_name"));
                bodyTypes.add(bodyType);
            }
            return bodyTypes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public BodyType save(BodyType bodyType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_VALUES_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bodyType.getName());

            statement.execute();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                bodyType.setId(result.getInt(1));
            }
            return bodyType;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(BodyType bodyType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_VALUES_SQL);
            statement.setString(1, bodyType.getName());
            statement.setInt(2, bodyType.getId());
            int rowCount = statement.executeUpdate();
            if (rowCount == 0) {
                throw new EntityNotFoundException("BodyType with id=" + bodyType.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(BodyType bodyType) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, bodyType.getId());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("BodyType with id=" + bodyType.getId() + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(Integer id) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("BodyType with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
