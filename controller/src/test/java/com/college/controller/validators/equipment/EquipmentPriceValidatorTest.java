package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentPriceValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentPriceValidator validator = new EquipmentPriceValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setPrice(2500.00);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Zero() {
        equipment.setPrice(0);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_PRICE,  response);
    }

    @Test
    void validate_shouldNotBeValid_Negative() {
        equipment.setPrice(-2.3F);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_PRICE,  response);
    }
}