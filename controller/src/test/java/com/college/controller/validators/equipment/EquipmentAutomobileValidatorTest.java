package com.college.controller.validators.equipment;

import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentAutomobileValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentAutomobileValidator validator = new EquipmentAutomobileValidator();

    @Test
    void validate_shouldBeValid() {
        Automobile automobile = new Automobile();
        automobile.setId(152);
        equipment.setAutomobile(automobile);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setAutomobile(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_AUTOMOBILE,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        equipment.setAutomobile(new Automobile());
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_AUTOMOBILE,  response);
    }

}