package com.college.controller.validators;

import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPhoneValidatorTest {
    private final UserPhoneValidator validator = new UserPhoneValidator();

    @Test
    void shouldBeValid() {
        String phoneNumber = "+37367292196";
        User user = new User();
        user.setTelephone(phoneNumber);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void shouldNotBeValid_tooShort() {
        String phoneNumber = "+3736729";
        User user = new User();
        user.setTelephone(phoneNumber);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void shouldNotBeValid_tooLong() {
        String phoneNumber = "+3736729587412546";
        User user = new User();
        user.setTelephone(phoneNumber);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void shouldNotBeValid_startsWithoutPlus() {
        String phoneNumber = "37367292196";
        User user = new User();
        user.setTelephone(phoneNumber);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void shouldNotBeValid_startsWithoutCountryCode() {
        String phoneNumber = "67292196";
        User user = new User();
        user.setTelephone(phoneNumber);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_PHONE, response);
    }
}