package com.college.controller.validators.equipment;

import com.college.model.entity.DriveType;
import com.college.model.entity.Equipment;
import com.college.model.entity.FuelType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentFuelTypeValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentFuelTypeValidator validator = new EquipmentFuelTypeValidator();

    @Test
    void validate_shouldBeValid() {
        FuelType fuelType = new FuelType();
        fuelType.setId(152);
        equipment.setFuelType(fuelType);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setFuelType(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_FUEL_TYPE,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        equipment.setFuelType(new FuelType());
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_FUEL_TYPE,  response);
    }
}