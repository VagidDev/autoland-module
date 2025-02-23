package com.college.model.database.implementations;

import com.college.model.entity.SuspensionType;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.SuspensionTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuspensionTypeImpl implements SuspensionTypeDAO {
    private static final String SELECT_SQL = "SELECT * FROM au_suspension_type";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM au_suspension_type WHERE st_id = ?";
    private static final String INSERT_VALUES_SQL = "INSERT INTO au_suspension_type (st_name) VALUES (?)";
    private static final String UPDATE_VALUES_SQL = "UPDATE au_suspension_type SET st_name = ? WHERE st_id = ?";
    private static final String DELETE_SQL = "DELETE FROM au_suspension_type WHERE st_id = ?";


    @Override
    public SuspensionType getById(Integer id) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                SuspensionType suspensionType = new SuspensionType();
                suspensionType.setId(resultSet.getInt("st_id"));
                suspensionType.setName(resultSet.getString("st_name"));
                return suspensionType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<SuspensionType> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<SuspensionType> suspensionTypes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);
            while (resultSet.next()) {
                SuspensionType suspensionType = new SuspensionType();
                suspensionType.setId(resultSet.getInt("st_id"));
                suspensionType.setName(resultSet.getString("st_name"));
                suspensionTypes.add(suspensionType);
            }
            return suspensionTypes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public SuspensionType save(SuspensionType suspensionType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VALUES_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, suspensionType.getName());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                suspensionType.setId(resultSet.getInt(1));
                return suspensionType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(SuspensionType suspensionType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VALUES_SQL);
            preparedStatement.setString(1, suspensionType.getName());
            preparedStatement.setInt(2, suspensionType.getId());
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount == 0) {
                throw new EntityNotFoundException("SuspensionType with id=" + suspensionType.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(SuspensionType suspensionType) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, suspensionType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("SuspensionType with id=" + suspensionType.getId() + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(Integer id) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("SuspensionType with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
