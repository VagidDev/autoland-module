package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentEngineNameValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentEngineNameValidator validator = new EquipmentEngineNameValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setEngineName("V8");
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setEngineName(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_ENGINE_NAME,  response);
    }

    @Test
    void validate_shouldNotBeValid_Blank() {
        equipment.setEngineName("             ");
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_ENGINE_NAME,  response);
    }
}