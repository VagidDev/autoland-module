/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.implementations;

import com.college.model.Equipment;
import com.college.model.database.Database;
import com.college.model.database.interfaces.AutomobileDAO;
import com.college.model.database.interfaces.EquipmentDAO;
import com.college.model.keys.EquipmentId;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class EquipmentImpl implements EquipmentDAO {
    private static final String GET_BY_ID_QUERY = "SELECT * FROM FullEquipment WHERE e_auto_id = ? AND e_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM FullEquipment";
    private static final String INSERT_QUERY = "INSERT INTO au_equipment (e_auto_id, e_id, e_name, e_engine_name, e_engine_id, e_engine_volume, e_horse_power,"
                                            + " e_susp_id, e_drive_id, e_gearbox_id, e_speed_count, e_fuel_id, e_interior, e_body_kit, e_weigth, e_price) "
                                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_equipment\n"
                                            + "SET e_name = ?, e_engine_name = ?, e_engine_id = ?, e_engine_volume = ?, e_horse_power = ?,"
                                            + " e_susp_id = ?, e_drive_id = ?, e_gearbox_id = ?, e_speed_count = ?, e_fuel_id = ?, e_interior = ?, e_body_kit = ?, e_weigth = ?, e_price = ?\n"
                                            + "WHERE e_auto_id = ? AND e_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_equipment WHERE e_auto_id = ? AND e_id = ?;";
    

    private final AutomobileDAO automobileRepository;

    public EquipmentImpl(AutomobileDAO automobileRepository) {
        this.automobileRepository = automobileRepository;
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
                equipment.setAutomobile(
                        automobileRepository.getById(result.getInt("e_auto_id"))
                );
                equipment.setId(result.getInt("e_id"));
                equipment.setName(result.getString("e_name"));
                equipment.setEngineName(result.getString("e_engine_name"));
                equipment.setEngineType(result.getString("et_name"));
                equipment.setEngineVolume(result.getFloat("e_engine_volume"));
                equipment.setHorsepower(result.getInt("e_horse_power"));
                equipment.setSuspensiveType(result.getString("st_name"));
                equipment.setDriveType(result.getString("dt_name"));
                equipment.setGearboxType(result.getString("gt_name"));
                equipment.setSpeedCount(result.getInt("e_speed_count"));
                equipment.setFuelType(result.getString("ft_name"));
                equipment.setInterior(result.getString("e_interior"));
                equipment.setBodyKit(result.getString("e_body_kit"));
                equipment.setWeigth(result.getInt("e_weigth"));
                equipment.setPrice(result.getDouble("e_price"));

                return equipment;
            }
            throw new RuntimeException("Equipment not found!");
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
                equipment.setAutomobile(
                        automobileRepository.getById(result.getInt("e_auto_id"))
                );
                equipment.setId(result.getInt("e_id"));
                equipment.setName(result.getString("e_name"));
                equipment.setEngineName(result.getString("e_engine_name"));
                equipment.setEngineType(result.getString("et_name"));
                equipment.setEngineVolume(result.getFloat("e_engine_volume"));
                equipment.setHorsepower(result.getInt("e_horse_power"));
                equipment.setSuspensiveType(result.getString("st_name"));
                equipment.setDriveType(result.getString("dt_name"));
                equipment.setGearboxType(result.getString("gt_name"));
                equipment.setSpeedCount(result.getInt("e_speed_count"));
                equipment.setFuelType(result.getString("ft_name"));
                equipment.setInterior(result.getString("e_interior"));
                equipment.setBodyKit(result.getString("e_body_kit"));
                equipment.setWeigth(result.getInt("e_weigth"));
                equipment.setPrice(result.getDouble("e_price"));
                equipments.add(equipment);
            }
            return equipments;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private int getIdByName(String name, String tableName) {
        try (Connection conn = Database.getConnection()) {
            String query = "CALL get_" + tableName + "_id(?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Equipment save(Equipment t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);

            statement.setInt(1, t.getAutomobile().getId());
            statement.setInt(2, t.getId());
            statement.setString(3, t.getName());
            statement.setString(4, t.getEngineName());
            statement.setInt(5, getIdByName(t.getEngineName(), "engine_type"));
            statement.setDouble(6, t.getEngineVolume());
            statement.setInt(7, t.getHorsepower());
            statement.setInt(8, getIdByName(t.getSuspensiveType(), "suspensive_type"));
            statement.setInt(9, getIdByName(t.getDriveType(), "drive_type"));
            statement.setInt(10, getIdByName(t.getGearboxType(), "gearbox_type"));
            statement.setInt(11, t.getSpeedCount());
            statement.setInt(12, getIdByName(t.getFuelType(), "fuel_type"));
            statement.setString(13, t.getInterior());
            statement.setString(14, t.getBodyKit());
            statement.setInt(15, t.getWeigth());
            statement.setDouble(16, t.getPrice());

            statement.execute();

            return t;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public boolean update(Equipment t) {
        try (Connection conn = Database.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);

            statement.setString(1, t.getName());
            statement.setString(2, t.getEngineName());
            statement.setInt(3, getIdByName(t.getEngineName(), "engine_type"));
            statement.setDouble(4, t.getEngineVolume());
            statement.setInt(5, t.getHorsepower());
            statement.setInt(6, getIdByName(t.getSuspensiveType(), "suspensive_type"));
            statement.setInt(7, getIdByName(t.getDriveType(), "drive_type"));
            statement.setInt(8, getIdByName(t.getGearboxType(), "gearbox_type"));
            statement.setInt(9, t.getSpeedCount());
            statement.setInt(10, getIdByName(t.getFuelType(), "fuel_type"));
            statement.setString(11, t.getInterior());
            statement.setString(12, t.getBodyKit());
            statement.setInt(13, t.getWeigth());
            statement.setDouble(14, t.getPrice());
            statement.setInt(15, t.getAutomobile().getId());
            statement.setInt(16, t.getId());

            int result = statement.executeUpdate();

            return result != 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Equipment t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, t.getAutomobile().getId());
            statement.setInt(2, t.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(EquipmentId id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id.getAutomobileId());
            statement.setInt(2, id.getEquipmentId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
