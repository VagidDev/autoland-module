package com.college.controller.validators.equipment;

import com.college.model.entity.DriveType;
import com.college.model.entity.EngineType;
import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentEngineTypeValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentEngineTypeValidator validator = new EquipmentEngineTypeValidator();

    @Test
    void validate_shouldBeValid() {
        EngineType engineType = new EngineType();
        engineType.setId(152);
        equipment.setEngineType(engineType);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setEngineType(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_ENGINE_TYPE,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        equipment.setEngineType(new EngineType());
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_ENGINE_TYPE,  response);
    }


}