package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;
import com.college.model.entity.GearboxType;
import com.college.model.entity.SuspensionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentSuspensionTypeValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentSuspensionTypeValidator validator = new EquipmentSuspensionTypeValidator();

    @Test
    void validate_shouldBeValid() {
        SuspensionType suspensionType = new SuspensionType();
        suspensionType.setId(152);
        equipment.setSuspensionType(suspensionType);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setSuspensionType(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_SUSPENSION_TYPE,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        equipment.setSuspensionType(new SuspensionType());
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_SUSPENSION_TYPE,  response);
    }
}