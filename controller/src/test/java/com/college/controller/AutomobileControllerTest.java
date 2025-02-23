package com.college.controller;


import com.college.model.entity.Automobile;
import com.college.model.entity.BodyType;
import com.college.model.entity.Equipment;
import com.college.model.database.interfaces.AutomobileDAO;
import com.college.model.database.interfaces.EquipmentDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AutomobileControllerTest {
    @Mock
    private AutomobileDAO automobileDAO;
    @Mock
    private EquipmentDAO equipmentDAO;

    @InjectMocks
    private AutomobileController automobileController;

    @BeforeAll
    static void setUp() {

    }

    @Test
    void getAllAutomobiles_shouldReturnListOfAutomobiles() {
        Mockito.when(automobileDAO.getAll()).thenReturn(List.of(
                    new Automobile(1, "First Mark", "First Model", new BodyType(1, "Body Type"), 5, 2025),
                    new Automobile(2, "Second Mark", "Second Model", new BodyType(1, "Body Type"), 5, 2025),
                    new Automobile(3, "Third Mark", "Third Model", new BodyType(1, "Body Type"), 5, 2025),
                    new Automobile(4, "Fourth Mark", "Fourth Model", new BodyType(1, "Body Type"), 5, 2025)
                )
        );

        List<Automobile> automobiles = automobileController.getAllAutomobiles();
        assertEquals(4, automobiles.size());
    }

    @Test
    void getAutomobileById_shouldReturnAutomobile() {
        Mockito.when(automobileDAO.getById(1))
                .thenReturn(new Automobile(1, "First Mark", "First Model", new BodyType(1, "Body Type"), 5, 2025));

        Automobile automobile = automobileController.getAutomobileById(1);
        assertNotNull(automobile);
    }

    @Test
    void getEquipmentsByAutomobile_shouldReturnEquipments() {
        Mockito.when(equipmentDAO.getByAuto(Mockito.any(Automobile.class)))
                .thenReturn(List.of(
                        new Equipment(),
                        new Equipment(),
                        new Equipment(),
                        new Equipment()
                ));
        Automobile automobile = new Automobile();
        List<Equipment> equipments = automobileController.getEquipmentsByAutomobile(automobile);
        assertEquals(4, equipments.size());
    }

    @Test
    void getEquipmentsByAutomobileId_shouldReturnEquipment() {
        Mockito.when(automobileDAO.getById(Mockito.anyInt())).thenReturn(new Automobile());
        Mockito.when(equipmentDAO.getByAuto(Mockito.any(Automobile.class)))
                .thenReturn(List.of(
                        new Equipment(),
                        new Equipment(),
                        new Equipment(),
                        new Equipment()
                ));

        List<Equipment> equipments = automobileController.getEquipmentsByAutomobileId(1);
        assertEquals(4, equipments.size());
    }

}