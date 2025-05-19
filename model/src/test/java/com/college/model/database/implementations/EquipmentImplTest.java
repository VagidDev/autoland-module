package com.college.model.database.implementations;

import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.*;
import com.college.model.entity.keys.EquipmentId;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EquipmentImplTest {

    private static EquipmentImpl equipmentImpl;
    private static AutomobileImpl automobileImpl;
    private static EngineTypeImpl engineTypeImpl;
    private static SuspensionTypeImpl suspensionTypeImpl;
    private static DriveTypeImpl driveTypeImpl;
    private static GearboxTypeImpl gearboxTypeImpl;
    private static FuelTypeImpl fuelTypeImpl;

    private static Equipment testEquipment;

    @BeforeAll
    static void setUp() {
        equipmentImpl = new EquipmentImpl();
        automobileImpl = new AutomobileImpl();
        engineTypeImpl = new EngineTypeImpl();
        suspensionTypeImpl = new SuspensionTypeImpl();
        driveTypeImpl = new DriveTypeImpl();
        gearboxTypeImpl = new GearboxTypeImpl();
        fuelTypeImpl = new FuelTypeImpl();

        testEquipment = createEquipment();
    }

    public static Equipment createEquipment() {
        Automobile automobile = automobileImpl.getById(1);
        EngineType engineType = engineTypeImpl.getById(1);
        SuspensionType suspensionType = suspensionTypeImpl.getById(1);
        DriveType driveType = driveTypeImpl.getById(1);
        GearboxType gearboxType = gearboxTypeImpl.getById(1);
        FuelType fuelType = fuelTypeImpl.getById(1);

        Equipment equipment = new Equipment();
        equipment.setAutomobile(automobile);
        equipment.setName("Sport Edition");
        equipment.setEngineName("V8 Turbo");
        equipment.setEngineType(engineType);
        equipment.setEngineVolume(4.0f);
        equipment.setHorsepower(450);
        equipment.setSuspensionType(suspensionType);
        equipment.setDriveType(driveType);
        equipment.setGearboxType(gearboxType);
        equipment.setSpeedCount(7);
        equipment.setFuelType(fuelType);
        equipment.setInterior("Leather");
        equipment.setBodyKit("Sport");
        equipment.setWeight(1600);
        equipment.setPrice(75000.0);
        equipment.setImagePath("/images/equipment1.jpg");

        return equipment;
    }

    @Test
    @Order(1)
    void save_shouldSaveEquipmentWithGeneratedId() {
        Equipment savedEquipment = equipmentImpl.save(testEquipment);
        assertNotNull(savedEquipment);
        assertNotNull(savedEquipment.getId());
        testEquipment = savedEquipment;
    }

    @Test
    @Order(2)
    void getById_shouldReturnEquipmentWithId() {
        Equipment equipment = equipmentImpl.getById(testEquipment.getId());
        assertNotNull(equipment);
        assertEquals("Sport Edition", equipment.getName());
    }

    @Test
    @Order(3)
    void getAll_shouldReturnAllEquipments() {
        List<Equipment> equipments = equipmentImpl.getAll();
        assertFalse(equipments.isEmpty());
    }

    @Test
    @Order(4)
    void getByAuto_shouldReturnEquipmentsForAutomobile() {
        List<Equipment> equipments = equipmentImpl.getByAuto(testEquipment.getAutomobile());
        assertFalse(equipments.isEmpty());
    }

    @Test
    @Order(5)
    void update_shouldUpdateEquipment() {
        testEquipment.setName("Updated Edition");
        equipmentImpl.update(testEquipment);

        Equipment updatedEquipment = equipmentImpl.getById(testEquipment.getId());
        assertEquals("Updated Edition", updatedEquipment.getName());
    }

    @Test
    @Order(6)
    void delete_shouldRemoveEquipment() throws CascadeDependencyException {
        equipmentImpl.delete(testEquipment);
        Equipment deletedEquipment = equipmentImpl.getById(testEquipment.getId());
        assertNull(deletedEquipment);
    }

    @Test
    @Order(7)
    void searchByModelAndMark_shouldReturnEquipmentsSimilarToFord() {
        String serachString = "ford";
        List<Equipment> fordEquipment = equipmentImpl.searchByModelAndMark(serachString);
        assertFalse(fordEquipment.isEmpty());
    }
}
