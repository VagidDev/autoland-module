package com.college.model.database.implementations;

import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.database.Database;
import com.college.model.database.interfaces.*;
import com.college.model.entity.keys.EquipmentId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Vagid Zibliuc
 */
public class EquipmentImpl extends AbstractCRUDRepository<EquipmentId, Equipment> implements EquipmentDAO {

    private static final String GET_BY_ID_HQL = "FROM Equipment WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM Equipment";


    private static final String GET_BY_AUTO_QUERY = "SELECT * FROM au_equipments WHERE e_auto_id = ?";
    private static final String GET_FREE_ID = "SELECT MAX(e_id) FROM au_equipments WHERE e_auto_id = ?";

    private AutomobileDAO automobileRepository;
    private EngineTypeDAO engineTypeDAO;
    private SuspensionTypeDAO suspensionTypeDAO;
    private DriveTypeDAO driveTypeDAO;
    private GearboxTypeDAO gearboxTypeDAO;
    private FuelTypeDAO fuelTypeDAO;

    public EquipmentImpl(AutomobileDAO automobileRepository, EngineTypeDAO engineTypeDAO, SuspensionTypeDAO suspensionTypeDAO,
                         DriveTypeDAO driveTypeDAO, GearboxTypeDAO gearboxTypeDAO, FuelTypeDAO fuelTypeDAO) {
        this.automobileRepository = automobileRepository;
        this.engineTypeDAO = engineTypeDAO;
        this.suspensionTypeDAO = suspensionTypeDAO;
        this.driveTypeDAO = driveTypeDAO;
        this.gearboxTypeDAO = gearboxTypeDAO;
        this.fuelTypeDAO = fuelTypeDAO;
    }

    public EquipmentImpl() {}

    public Equipment getBySimpleId(int autoId, int equipId) {
        EquipmentId id = new EquipmentId(autoId, equipId);
        return getById(id);
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


    @Override
    protected Class<Equipment> getEntityClass() {
        return Equipment.class;
    }

    @Override
    protected String getByIdQuery() {
        return GET_BY_ID_HQL;
    }

    @Override
    protected String getAllQuery() {
        return GET_ALL_HQL;
    }
}
