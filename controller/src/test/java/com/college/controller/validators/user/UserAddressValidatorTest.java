package com.college.controller.validators.user;

import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAddressValidatorTest {
    private final UserAddressValidator validator = new UserAddressValidator();

    @Test
    void shouldBeValid() {
        User user = new User();
        user.setAddress("Some Address");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void shouldNotBeValid_NullAddress() {
        User user = new User();
        user.setAddress(null);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_ADDRESS, response);
    }

    @Test
    void shouldNotBeValid_EmptyAddress() {
        User user = new User();
        user.setAddress("");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_ADDRESS, response);
    }

    @Test
    void shouldNotBeValid_SpacedAddress() {
        User user = new User();
        user.setAddress("              ");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_ADDRESS, response);
    }

}