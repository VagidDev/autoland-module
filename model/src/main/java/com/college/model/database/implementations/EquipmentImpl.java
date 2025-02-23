package com.college.model.database.implementations;

import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.*;
import com.college.model.keys.EquipmentId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Vagid Zibliuc
 */
public class EquipmentImpl implements EquipmentDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_equipments WHERE e_auto_id = ? AND e_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_equipments";
    private static final String INSERT_QUERY = "INSERT INTO au_equipments (e_auto_id, e_id, e_name, e_engine_name, e_engine_id, e_engine_volume, e_horse_power,"
            + " e_susp_id, e_drive_id, e_gearbox_id, e_speed_count, e_fuel_id, e_interior, e_body_kit, e_weigth, e_price, e_image) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_equipments\n"
            + "SET e_name = ?, e_engine_name = ?, e_engine_id = ?, e_engine_volume = ?, e_horse_power = ?,"
            + " e_susp_id = ?, e_drive_id = ?, e_gearbox_id = ?, e_speed_count = ?, e_fuel_id = ?, e_interior = ?, "
            + "e_body_kit = ?, e_weigth = ?, e_price = ?, e_image = ?\n"
            + "WHERE e_auto_id = ? AND e_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_equipments WHERE e_auto_id = ? AND e_id = ?;";
    private static final String GET_BY_AUTO_QUERY = "SELECT * FROM au_equipments WHERE e_auto_id = ?";
    private static final String GET_FREE_ID = "SELECT MAX(e_id) FROM au_equipments WHERE e_auto_id = ?";

    private final AutomobileDAO automobileRepository;
    private final EngineTypeDAO engineTypeDAO;
    private final SuspensionTypeDAO suspensionTypeDAO;
    private final DriveTypeDAO driveTypeDAO;
    private final GearboxTypeDAO gearboxTypeDAO;
    private final FuelTypeDAO fuelTypeDAO;

    public EquipmentImpl(AutomobileDAO automobileRepository, EngineTypeDAO engineTypeDAO, SuspensionTypeDAO suspensionTypeDAO,
                         DriveTypeDAO driveTypeDAO, GearboxTypeDAO gearboxTypeDAO, FuelTypeDAO fuelTypeDAO) {
        this.automobileRepository = automobileRepository;
        this.engineTypeDAO = engineTypeDAO;
        this.suspensionTypeDAO = suspensionTypeDAO;
        this.driveTypeDAO = driveTypeDAO;
        this.gearboxTypeDAO = gearboxTypeDAO;
        this.fuelTypeDAO = fuelTypeDAO;
    }

    public Equipment getBySimpleId(int autoId, int equipId) {
        EquipmentId id = new EquipmentId(autoId, equipId);
        return getById(id);
    }

    @Override
    public Equipment getById(EquipmentId id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id.getAutomobileId());
            statement.setInt(2, id.getEquipmentId());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Equipment equipment = new Equipment();
                equipment.setId(id);
                equipment.setName(result.getString("e_name"));
                equipment.setEngineName(result.getString("e_engine_name"));
                equipment.setEngineType(engineTypeDAO.getById(result.getInt("e_engine_id")));
                equipment.setEngineVolume(result.getFloat("e_engine_volume"));
                equipment.setHorsepower(result.getInt("e_horse_power"));
                equipment.setSuspensionType(suspensionTypeDAO.getById(result.getInt("e_susp_id")));
                equipment.setDriveType(driveTypeDAO.getById(result.getInt("e_drive_id")));
                equipment.setGearboxType(gearboxTypeDAO.getById(result.getInt("e_gearbox_id")));
                equipment.setSpeedCount(result.getInt("e_speed_count"));
                equipment.setFuelType(fuelTypeDAO.getById(result.getInt("e_fuel_id")));
                equipment.setInterior(result.getString("e_interior"));
                equipment.setBodyKit(result.getString("e_body_kit"));
                equipment.setWeight(result.getInt("e_weight"));
                equipment.setPrice(result.getDouble("e_price"));
                equipment.setImagePath(result.getString("e_image"));

                return equipment;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Equipment> getAll() {
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            List<Equipment> equipments = new ArrayList<>();
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
            while (result.next()) {
                Equipment equipment = new Equipment();
                EquipmentId id = new EquipmentId(
                        result.getInt("e_auto_id"),
                        result.getInt("e_id")
                );

                equipment.setId(id);
                equipment.setName(result.getString("e_name"));
                equipment.setEngineName(result.getString("e_engine_name"));
                equipment.setEngineType(engineTypeDAO.getById(result.getInt("e_engine_id")));
                equipment.setEngineVolume(result.getFloat("e_engine_volume"));
                equipment.setHorsepower(result.getInt("e_horse_power"));
                equipment.setSuspensionType(suspensionTypeDAO.getById(result.getInt("e_susp_id")));
                equipment.setDriveType(driveTypeDAO.getById(result.getInt("e_drive_id")));
                equipment.setGearboxType(gearboxTypeDAO.getById(result.getInt("e_gearbox_id")));
                equipment.setSpeedCount(result.getInt("e_speed_count"));
                equipment.setFuelType(fuelTypeDAO.getById(result.getInt("e_fuel_id")));
                equipment.setInterior(result.getString("e_interior"));
                equipment.setBodyKit(result.getString("e_body_kit"));
                equipment.setWeight(result.getInt("e_weight"));
                equipment.setPrice(result.getDouble("e_price"));
                equipment.setImagePath(result.getString("e_image"));

                equipments.add(equipment);
            }
            return equipments;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    //better this method create as a stored procedure in database
    private EquipmentId getActualId(int autoId) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_FREE_ID);
            statement.setInt(1, autoId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int newId = resultSet.getInt(1) + 1;
                return new EquipmentId(autoId, newId);
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Equipment save(Equipment t) {
        try (Connection conn = Database.getConnection()) {
            t.setId(getActualId(t.getId().getAutomobileId()));

            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);

            statement.setInt(1, t.getId().getAutomobileId());
            statement.setInt(2, t.getId().getEquipmentId());
            statement.setString(3, t.getName());
            statement.setString(4, t.getEngineName());
            statement.setInt(5, t.getEngineType().getId());
            statement.setDouble(6, t.getEngineVolume());
            statement.setInt(7, t.getHorsepower());
            statement.setInt(8, t.getSuspensionType().getId());
            statement.setInt(9, t.getDriveType().getId());
            statement.setInt(10, t.getGearboxType().getId());
            statement.setInt(11, t.getSpeedCount());
            statement.setInt(12, t.getFuelType().getId());
            statement.setString(13, t.getInterior());
            statement.setString(14, t.getBodyKit());
            statement.setInt(15, t.getWeight());
            statement.setDouble(16, t.getPrice());
            statement.setString(17, t.getImagePath());

            statement.execute();

            return t;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void update(Equipment t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);

            statement.setString(1, t.getName());
            statement.setString(2, t.getEngineName());
            statement.setInt(3, t.getEngineType().getId());
            statement.setDouble(4, t.getEngineVolume());
            statement.setInt(5, t.getHorsepower());
            statement.setInt(6, t.getSuspensionType().getId());
            statement.setInt(7, t.getDriveType().getId());
            statement.setInt(8, t.getGearboxType().getId());
            statement.setInt(9, t.getSpeedCount());
            statement.setInt(10, t.getFuelType().getId());
            statement.setString(11, t.getInterior());
            statement.setString(12, t.getBodyKit());
            statement.setInt(13, t.getWeight());
            statement.setDouble(14, t.getPrice());
            statement.setString(15, t.getImagePath());
            statement.setInt(16, t.getId().getAutomobileId());
            statement.setInt(17, t.getId().getEquipmentId());

            int result = statement.executeUpdate();

            if (result == 0) {
                throw new EntityNotFoundException("Equipment with auto_id= " + t.getId().getAutomobileId() + " id=" + t.getId().getEquipmentId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Equipment t) throws CascadeDependencyException {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, t.getId().getAutomobileId());
            statement.setInt(2, t.getId().getEquipmentId());
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("Equipment with auto_id= " + t.getId().getAutomobileId() + " id=" + t.getId() + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(EquipmentId id) throws CascadeDependencyException {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id.getAutomobileId());
            statement.setInt(2, id.getEquipmentId());
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("Equipment with auto_id= " + id.getAutomobileId() + " id=" + id.getEquipmentId() + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Equipment> getByAuto(Automobile automobile) {
        try (Connection conn = Database.getConnection()) {
            List<Equipment> equipments = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(GET_BY_AUTO_QUERY);
            statement.setInt(1, automobile.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Equipment equipment = new Equipment();
                equipment.setId(new EquipmentId(
                        automobile.getId(),
                        result.getInt("e_id")
                ));
                equipment.setName(result.getString("e_name"));
                equipment.setEngineName(result.getString("e_engine_name"));
                equipment.setEngineType(engineTypeDAO.getById(result.getInt("e_engine_id")));
                equipment.setEngineVolume(result.getFloat("e_engine_volume"));
                equipment.setHorsepower(result.getInt("e_horse_power"));
                equipment.setSuspensionType(suspensionTypeDAO.getById(result.getInt("e_susp_id")));
                equipment.setDriveType(driveTypeDAO.getById(result.getInt("e_drive_id")));
                equipment.setGearboxType(gearboxTypeDAO.getById(result.getInt("e_gearbox_id")));
                equipment.setSpeedCount(result.getInt("e_speed_count"));
                equipment.setFuelType(fuelTypeDAO.getById(result.getInt("e_fuel_id")));
                equipment.setInterior(result.getString("e_interior"));
                equipment.setBodyKit(result.getString("e_body_kit"));
                equipment.setWeight(result.getInt("e_weigth"));
                equipment.setPrice(result.getDouble("e_price"));
                equipment.setImagePath(result.getString("e_image"));

                equipments.add(equipment);
            }
            return equipments;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }



}
