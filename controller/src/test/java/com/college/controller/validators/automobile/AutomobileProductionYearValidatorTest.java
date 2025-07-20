package com.college.controller.validators.automobile;

import com.college.model.entity.Automobile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomobileProductionYearValidatorTest {
    private final Automobile automobile = new Automobile();
    private final AutomobileProductionYearValidator automobileProductionYearValidator = new AutomobileProductionYearValidator();

    @Test
    void validate_shouldBeValid() {
        automobile.setProdYear(2023);
        AutomobileValidationResponse response = automobileProductionYearValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_TooOldDate() {
        automobile.setProdYear(1000);
        AutomobileValidationResponse response = automobileProductionYearValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_PRODUCTION_YEAR, response);
    }

    @Test
    void validate_shouldNotBeValid_YearDoesNotExistYet() {
        automobile.setPlaceCount(2027);
        AutomobileValidationResponse response = automobileProductionYearValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_PRODUCTION_YEAR, response);
    }
}