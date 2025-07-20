package com.college.controller.validators.automobile;

import com.college.model.entity.Automobile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomobileModelValidatorTest {
    private final Automobile automobile = new Automobile();
    private final AutomobileModelValidator automobileModelValidator = new AutomobileModelValidator();

    @Test
    void validate_shouldBeValid() {
        automobile.setModel("i 3");
        AutomobileValidationResponse response = automobileModelValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        automobile.setModel(null);
        AutomobileValidationResponse response = automobileModelValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_MODEL, response);
    }

    @Test
    void validate_shouldNotBeValid_Empty() {
        automobile.setModel("                   ");
        AutomobileValidationResponse response = automobileModelValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_MODEL, response);
    }
}