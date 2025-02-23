package com.college.model.database.implementations;

import com.college.model.entity.GearboxType;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.GearboxTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GearboxTypeImpl implements GearboxTypeDAO {
    private static final String SELECT_SQL = "SELECT * FROM au_gearbox_type";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM au_gearbox_type WHERE gt_id = ?";
    private static final String INSERT_VALUES_SQL = "INSERT INTO au_gearbox_type (gt_name) VALUES (?)";
    private static final String UPDATE_VALUES_SQL = "UPDATE au_gearbox_type SET gt_name = ? WHERE gt_id = ?";
    private static final String DELETE_SQL = "DELETE FROM au_gearbox_type WHERE gt_id = ?";

    @Override
    public GearboxType getById(Integer id) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                GearboxType gearboxType = new GearboxType();
                gearboxType.setId(resultSet.getInt("gt_id"));
                gearboxType.setName(resultSet.getString("gt_name"));
                return gearboxType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<GearboxType> getAll() {
        try (Connection connection = Database.getConnection()) {
            List<GearboxType> gearboxTypes = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SQL);

            while (resultSet.next()) {
                GearboxType gearboxType = new GearboxType();
                gearboxType.setId(resultSet.getInt("gt_id"));
                gearboxType.setName(resultSet.getString("gt_name"));
                gearboxTypes.add(gearboxType);
            }
            return gearboxTypes;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public GearboxType save(GearboxType gearboxType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_VALUES_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, gearboxType.getName());

            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                gearboxType.setId(resultSet.getInt(1));
                return gearboxType;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(GearboxType gearboxType) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_VALUES_SQL);
            statement.setString(1, gearboxType.getName());
            statement.setInt(2, gearboxType.getId());

            int rowCount = statement.executeUpdate();

            if (rowCount == 0) {
                throw new EntityNotFoundException("GearboxType with id=" + gearboxType.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(GearboxType gearboxType) throws CascadeDependencyException {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, gearboxType.getId());

            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("GearboxType with id=" + gearboxType.getId() + " using in other tables");
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
            throw new CascadeDependencyException("GearboxType with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
