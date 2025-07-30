package com.college.controller.validators.equipment;

import com.college.model.entity.Automobile;
import com.college.model.entity.DriveType;
import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentDriveTypeValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentDriveTypeValidator validator = new EquipmentDriveTypeValidator();

    @Test
    void validate_shouldBeValid() {
        DriveType driveType = new DriveType();
        driveType.setId(152);
        equipment.setDriveType(driveType);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setDriveType(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_DRIVE_TYPE,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        equipment.setDriveType(new DriveType());
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_DRIVE_TYPE,  response);
    }

}