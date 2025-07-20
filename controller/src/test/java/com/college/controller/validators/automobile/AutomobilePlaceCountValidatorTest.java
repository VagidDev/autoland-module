package com.college.controller.validators.automobile;

import com.college.model.entity.Automobile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomobilePlaceCountValidatorTest {
    private final Automobile automobile = new Automobile();
    private final AutomobilePlaceCountValidator automobilePlaceCountValidator = new AutomobilePlaceCountValidator();

    @Test
    void validate_shouldBeValid() {
        automobile.setPlaceCount(7);
        AutomobileValidationResponse response = automobilePlaceCountValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_ZeroPlaces() {
        automobile.setPlaceCount(0);
        AutomobileValidationResponse response = automobilePlaceCountValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_PLACE_COUNT, response);
    }

    @Test
    void validate_shouldNotBeValid_NegativePlaces() {
        automobile.setPlaceCount(-5);
        AutomobileValidationResponse response = automobilePlaceCountValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_PLACE_COUNT, response);
    }
}