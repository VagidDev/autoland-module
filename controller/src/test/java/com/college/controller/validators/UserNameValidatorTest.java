package com.college.controller.validators;

import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNameValidatorTest {
    private final UserNameValidator userNameValidator = new UserNameValidator();

    @Test
    void shouldBeValid() {
        User user = new User();
        user.setName("John");
        UserValidationResponse response = userNameValidator.validate(user);
        assertEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void shouldNotBeValid_NullName() {
        User user = new User();
        user.setName(null);
        UserValidationResponse response = userNameValidator.validate(user);
        assertEquals(UserValidationResponse.INVALID_NAME, response);
    }

    @Test
    void shouldNotBeValid_EmptyName() {
        User user = new User();
        user.setName("");
        UserValidationResponse response = userNameValidator.validate(user);
        assertEquals(UserValidationResponse.INVALID_NAME, response);
    }

    @Test
    void shouldNotBeValid_SpacedName() {
        User user = new User();
        user.setName("          ");
        UserValidationResponse response = userNameValidator.validate(user);
        assertEquals(UserValidationResponse.INVALID_NAME, response);
    }

}