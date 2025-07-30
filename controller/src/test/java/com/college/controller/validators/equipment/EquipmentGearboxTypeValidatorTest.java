package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import com.college.model.entity.FuelType;
import com.college.model.entity.GearboxType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentGearboxTypeValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentGearboxTypeValidator validator = new EquipmentGearboxTypeValidator();

    @Test
    void validate_shouldBeValid() {
        GearboxType gearboxType = new GearboxType();
        gearboxType.setId(152);
        equipment.setGearboxType(gearboxType);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setGearboxType(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_GEARBOX_TYPE,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        equipment.setGearboxType(new GearboxType());
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_GEARBOX_TYPE,  response);
    }
}