package com.college.controller.validators.automobile;

import com.college.model.entity.Automobile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomobileMarkValidatorTest {
    private final Automobile automobile = new Automobile();
    private final AutomobileMarkValidator automobileMarkValidator = new AutomobileMarkValidator();

    @Test
    void validate_shouldBeValid() {
        automobile.setMark("BMW");
        AutomobileValidationResponse response = automobileMarkValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        automobile.setMark(null);
        AutomobileValidationResponse response = automobileMarkValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_MARK, response);
    }

    @Test
    void validate_shouldNotBeValid_Empty() {
        automobile.setMark("                   ");
        AutomobileValidationResponse response = automobileMarkValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_MARK, response);
    }
}