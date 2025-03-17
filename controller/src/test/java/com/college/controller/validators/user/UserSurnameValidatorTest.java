package com.college.controller.validators.user;

import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSurnameValidatorTest {
    private final UserSurnameValidator validator = new UserSurnameValidator();

    @Test
    void shouldBeValid() {
        User user = new User();
        user.setSurname("Surname");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void shouldNotBeValid_NullSurname() {
        User user = new User();
        user.setSurname(null);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_SURNAME, response);
    }

    @Test
    void shouldNotBeValid_EmptySurname() {
        User user = new User();
        user.setSurname("");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_SURNAME, response);
    }

    @Test
    void shouldNotBeValid_SpaceSurname() {
        User user = new User();
        user.setSurname("           ");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_SURNAME, response);
    }
}