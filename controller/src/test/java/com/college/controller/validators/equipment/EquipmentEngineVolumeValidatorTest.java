package com.college.controller.validators.equipment;

import com.college.model.entity.DriveType;
import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentEngineVolumeValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentEngineVolumeValidator validator = new EquipmentEngineVolumeValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setEngineVolume(5.3F);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldBeValid_Zero() {
        equipment.setEngineVolume(0);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Negative() {
        equipment.setEngineVolume(-2.3F);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_ENGINE_VOLUME,  response);
    }

    @Test
    void validate_shouldNotBeValid_TooMuch() {
        equipment.setEngineVolume(125F);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_ENGINE_VOLUME,  response);
    }
}