package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentWeightValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentWeightValidator validator = new EquipmentWeightValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setWeight(2500);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Zero() {
        equipment.setWeight(0);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_WEIGHT,  response);
    }

    @Test
    void validate_shouldNotBeValid_Negative() {
        equipment.setWeight(-1522);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_WEIGHT,  response);
    }
}