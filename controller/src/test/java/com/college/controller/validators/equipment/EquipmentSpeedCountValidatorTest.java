package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentSpeedCountValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentSpeedCountValidator validator = new EquipmentSpeedCountValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setSpeedCount(7);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Zero() {
        equipment.setSpeedCount(0);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_SPEED_COUNT,  response);
    }

    @Test
    void validate_shouldNotBeValid_Negative() {
        equipment.setSpeedCount(-6);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_SPEED_COUNT,  response);
    }

    @Test
    void validate_shouldNotBeValid_TooMuch() {
        equipment.setSpeedCount(125);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_SPEED_COUNT,  response);
    }
}