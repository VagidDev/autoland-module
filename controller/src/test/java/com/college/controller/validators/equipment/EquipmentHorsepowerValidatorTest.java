package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentHorsepowerValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentHorsepowerValidator validator = new EquipmentHorsepowerValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setHorsepower(250);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Zero() {
        equipment.setHorsepower(0);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_HORSEPOWER,  response);
    }

    @Test
    void validate_shouldNotBeValid_Negative() {
        equipment.setHorsepower(-25);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_HORSEPOWER,  response);
    }
}