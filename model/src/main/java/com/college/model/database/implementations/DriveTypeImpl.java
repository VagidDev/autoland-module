package com.college.model.database.implementations;

import com.college.model.entity.DriveType;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.DriveTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriveTypeImpl implements DriveTypeDAO {
    private static final String SELECT_SQL = "SELECT * FROM au_drive_type";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM au_drive_type WHERE dt_id = ?";
    private static final String INSERT_VALUES_SQL = "INSERT INTO au_drive_type (dt_name) VALUES (?)";
    private static final String UPDATE_VALUES_SQL = "UPDATE au_drive_type SET dt_name = ? WHERE dt_id = ?";
    private static final String DELETE_SQL = "DELETE FROM au_drive_type WHERE dt_id = ?";

    @Override
    public DriveType getById(Integer integer) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DriveType driveType = new DriveType();
                driveType.setId(resultSet.getInt("dt_id"));
                driveType.setName(resultSet.getString("dt_name"));
                return driveType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<DriveType> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<DriveType> driveTypes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);
            while (resultSet.next()) {
                DriveType driveType = new DriveType();
                driveType.setId(resultSet.getInt("dt_id"));
                driveType.setName(resultSet.getString("dt_name"));
                driveTypes.add(driveType);
            }
            return driveTypes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public DriveType save(DriveType driveType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_VALUES_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, driveType.getName());
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                driveType.setId(resultSet.getInt(1));
                return driveType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(DriveType driveType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_VALUES_SQL);
            statement.setString(1, driveType.getName());
            statement.setInt(2, driveType.getId());
            int rowCount = statement.executeUpdate();

            if (rowCount == 0) {
                throw new EntityNotFoundException("Drive type with id=" + driveType.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(DriveType driveType) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, driveType.getId());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("DriveType with id=" + driveType.getId() + " using in other tables");
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
            throw new CascadeDependencyException("DriveType with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
