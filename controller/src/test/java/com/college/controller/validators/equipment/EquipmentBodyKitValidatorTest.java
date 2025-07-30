package com.college.controller.validators.equipment;

import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentBodyKitValidatorTest {
    private final Equipment equipment = new Equipment();
    private final EquipmentBodyKitValidator validator = new EquipmentBodyKitValidator();

    @Test
    void validate_shouldBeValid() {
        equipment.setBodyKit("bodyKit");
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        equipment.setBodyKit(null);
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_BODY_KIT,  response);
    }

    @Test
    void validate_shouldNotBeValid_Blank() {
        equipment.setBodyKit("             ");
        EquipmentValidationResponse response = validator.validate(equipment);
        assertEquals(EquipmentValidationResponse.INVALID_BODY_KIT,  response);
    }

}