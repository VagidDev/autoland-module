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

    private final AutomobileDAO automobileRepository;

    public EquipmentImpl(AutomobileDAO automobileRepository) {
        this.automobileRepository = automobileRepository;
    }

    @Override
    public Equipment getById(EquipmentId id) {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM FullEquipment WHERE e_auto_id = ? AND e_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
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
            String query = "SELECT * FROM FullEquipment";
            ResultSet result = statement.executeQuery(query);
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

    @Override
    public Equipment save(Equipment t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Equipment t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Equipment t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(EquipmentId id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
