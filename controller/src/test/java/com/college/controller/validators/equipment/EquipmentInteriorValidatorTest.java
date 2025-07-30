package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentInteriorValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentInteriorValidator validator = new EquipmentInteriorValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setInterior("Leather");
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setInterior(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_INTERIOR, response);
    }

    @Test
    void validate_shouldNotBeValid_Blank() {
        equipment.setInterior("             ");
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_INTERIOR, response);
    }
}