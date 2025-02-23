package com.college.model.database.implementations;

import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.keys.EquipmentId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentImplTest {
    private AutomobileImpl automobileService = new AutomobileImpl(new BodyTypeImpl());
    private final EquipmentImpl service  = new EquipmentImpl(
            automobileService,
            new EngineTypeImpl(),
            new SuspensionTypeImpl(),
            new DriveTypeImpl(),
            new GearboxTypeImpl(),
            new FuelTypeImpl()
    );

    @Test
    void shouldReturnEquipmentForTesla() {
        Automobile auto = automobileService.getById(3);
        EquipmentId id = new EquipmentId(auto.getId(), 3);
        Equipment equipment = service.getById(id);
        assertEquals(id, equipment.getId());
    }

    @Test
    void shouldReturnEquipmentForTeslaBySimpleId() {
        Equipment equipment = service.getBySimpleId(3,3);
        assertEquals("Tesla", equipment.getAutomobile().getMark());
    }

}