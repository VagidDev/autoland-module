package com.college.controller;

import com.college.model.database.interfaces.EquipmentDAO;
import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.entity.keys.EquipmentId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EquipmentControllerTest {
    @Mock
    private EquipmentDAO equipmentDAO;
    @InjectMocks
    private EquipmentController equipmentController;

    @Test
    void getEquipment_shouldReturnEquipment() {
        Mockito.when(equipmentDAO.getById(Mockito.any(EquipmentId.class)))
                .thenReturn(new Equipment());

        Equipment equipment = equipmentController.getEquipment(new EquipmentId(1, 1));
        assertNotNull(equipment);
    }

    @Test
    void getEquipmentByAutomobile_shouldReturnEquipmentForAutomobile() {
        Mockito.when(equipmentDAO.getByAuto(Mockito.any(Automobile.class)))
                .thenReturn(List.of(
                        new Equipment(),
                        new Equipment()
                ));

        List<Equipment> equipments = equipmentController.getEquipmentByAutomobile(new Automobile());
        assertEquals(2, equipments.size());
    }

    @Test
    void getAllEquipments_shouldReturnAllEquipments() {
        Mockito.when(equipmentDAO.getAll())
                .thenReturn(List.of(
                        new Equipment(),
                        new Equipment(),
                        new Equipment()
                ));

        List<Equipment> equipments = equipmentController.getAllEquipments();
        assertEquals(3, equipments.size());
    }

    @Test
    void getCheapestEquipment_shouldReturnCheapestEquipments() {
        Equipment equipment1 = new Equipment();
        Equipment equipment2 = new Equipment();
        Equipment equipment3 = new Equipment();
        equipment1.setId(new EquipmentId(1, 1));
        equipment2.setId(new EquipmentId(1, 2));
        equipment3.setId(new EquipmentId(2, 1));

        Mockito.when(equipmentDAO.getAll()).thenReturn(List.of(equipment1, equipment2, equipment3));

        List<Equipment> equipment = equipmentController.getCheapestEquipmentForAutomobile();
        assertEquals(2, equipment.size());

    }

    @Test
    void searchAutomobile_shouldReturnAutomobileByItName() {
        //stupid test, but maybe in the future my controllers will have some logic, not only transferring information
        Mockito.when(equipmentDAO.searchByModelAndMark(Mockito.any(String.class)))
                .thenReturn(List.of(new Equipment(),new Equipment()));
        assertNotNull(equipmentDAO.searchByModelAndMark("Ford"));
    }
}