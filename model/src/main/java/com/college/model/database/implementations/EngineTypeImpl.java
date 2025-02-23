package com.college.model.database.implementations;

import com.college.model.entity.EngineType;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.EngineTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngineTypeImpl implements EngineTypeDAO {
    private static final String SELECT_SQL = "SELECT * FROM au_engine_type";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM au_engine_type WHERE et_id = ?";
    private static final String INSERT_VALUES_SQL = "INSERT INTO au_engine_type (et_name) VALUES (?)";
    private static final String UPDATE_VALUES_SQL = "UPDATE au_engine_type SET et_name = ? WHERE et_id = ?";
    private static final String DELETE_SQL = "DELETE FROM au_engine_type WHERE et_id = ?";

    @Override
    public EngineType getById(Integer id) {
        try (Connection connection = Database.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EngineType engineType = new EngineType();
                engineType.setId(resultSet.getInt("et_id"));
                engineType.setName(resultSet.getString("et_name"));
                return engineType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<EngineType> getAll() {
        try (Connection connection = Database.getConnection()){
            List<EngineType> engineTypes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);
            while (resultSet.next()) {
                EngineType engineType = new EngineType();
                engineType.setId(resultSet.getInt("bt_id"));
                engineType.setName(resultSet.getString("bt_name"));
                engineTypes.add(engineType);
            }
            return engineTypes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EngineType save(EngineType engineType) {
        try (Connection connection = Database.getConnection()){
            PreparedStatement statement = connection.prepareStatement(INSERT_VALUES_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, engineType.getName());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                engineType.setId(resultSet.getInt(1));
                return engineType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(EngineType engineType) {
        try (Connection connection = Database.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_VALUES_SQL);
            statement.setString(1, engineType.getName());
            statement.setInt(2, engineType.getId());
            int rowCount = statement.executeUpdate();

            if (rowCount == 0) {
                throw new EntityNotFoundException("EngineType with id=" + engineType.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(EngineType engineType) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, engineType.getId());
            statement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("EngineType with id=" + engineType.getId() + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(Integer id) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("EngineType with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
