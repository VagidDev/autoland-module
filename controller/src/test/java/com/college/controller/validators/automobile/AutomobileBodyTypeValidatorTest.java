package com.college.controller.validators.automobile;

import com.college.model.entity.Automobile;
import com.college.model.entity.BodyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutomobileBodyTypeValidatorTest {
    private final Automobile automobile = new Automobile();
    private final AutomobileBodyTypeValidator automobileBodyTypeValidator = new AutomobileBodyTypeValidator();

    @Test
    void validate_shouldBeValid() {
        automobile.setBodyType(new BodyType(1, "SUV"));
        AutomobileValidationResponse response = automobileBodyTypeValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_NullBodyType() {
        automobile.setBodyType(null);
        AutomobileValidationResponse response = automobileBodyTypeValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_BODY_TYPE, response);
    }

    @Test
    void validate_shouldNotBeValid_EmptyBodyType() {
        automobile.setBodyType(new  BodyType());
        AutomobileValidationResponse response = automobileBodyTypeValidator.validate(automobile);
        assertEquals(AutomobileValidationResponse.INVALID_BODY_TYPE, response);
    }
}