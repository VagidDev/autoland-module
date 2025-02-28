package com.college.model.database.implementations;

import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentImplTest {
    //write tests
    private EquipmentImpl equipmentImpl = new EquipmentImpl();

    @Test
    void getAll_shouldReturnAllEquipments() {
        List<Equipment> equipments = equipmentImpl.getAll();

        equipments.forEach(System.out::println);
        assertFalse(equipments.isEmpty());
    }

    //need to do
    @Test
    void save_shouldSaveEquipmentWithGeneratedId() {
        List<Equipment> equipments = equipmentImpl.getAll();

        equipments.forEach(System.out::println);
        assertFalse(equipments.isEmpty());
    }
}