package com.college.model.database.implementations;

import com.college.model.FuelType;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.FuelTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuelTypeImpl implements FuelTypeDAO {
    private static final String SELECT_SQL = "SELECT * FROM au_fuel_type";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM au_fuel_type WHERE ft_id = ?";
    private static final String INSERT_VALUES_SQL = "INSERT INTO au_fuel_type (ft_name) VALUES (?)";
    private static final String UPDATE_VALUES_SQL = "UPDATE au_fuel_type SET ft_name = ? WHERE ft_id = ?";
    private static final String DELETE_SQL = "DELETE FROM au_fuel_type WHERE ft_id = ?";

    @Override
    public FuelType getById(Integer id) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FuelType fuelType = new FuelType();
                fuelType.setId(resultSet.getInt("ft_id"));
                fuelType.setName(resultSet.getString("ft_name"));
                return fuelType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<FuelType> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<FuelType> fuelTypes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);
            while (resultSet.next()) {
                FuelType fuelType = new FuelType();
                fuelType.setId(resultSet.getInt("ft_id"));
                fuelType.setName(resultSet.getString("ft_name"));
                fuelTypes.add(fuelType);
            }
            return fuelTypes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public FuelType save(FuelType fuelType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_VALUES_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, fuelType.getName());

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                fuelType.setId(resultSet.getInt(1));
                return fuelType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(FuelType fuelType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_VALUES_SQL);
            statement.setString(1, fuelType.getName());
            statement.setInt(2, fuelType.getId());

            int rowCount = statement.executeUpdate();

            if (rowCount == 0) {
                throw new EntityNotFoundException("FuelType with id=" + fuelType.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(FuelType fuelType) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, fuelType.getId());

            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("FuelType with id=" + fuelType.getId() + " using in other tables");
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
            throw new CascadeDependencyException("FuelType with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
