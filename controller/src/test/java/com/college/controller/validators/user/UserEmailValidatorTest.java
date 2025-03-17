package com.college.controller.validators.user;

import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEmailValidatorTest {

    private final UserEmailValidator validator = new UserEmailValidator();

    @Test
    void shouldBeValid() {
        User user = new User();
        user.setEmail("test@gmail.com");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void shouldNotBeValidBecauseEmailIsNull() {
        User user = new User();
        user.setEmail(null);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_EMAIL, response);
    }

    @Test
    void shouldNotBeValidBecauseEmailEndsWithDot() {
        User user = new User();
        user.setEmail("test@gmail.com.");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_EMAIL, response);
    }

    @Test
    void shouldNotBeValidBecauseEmailDoNotEndsWithGmail() {
        User user = new User();
        user.setEmail("test@email.com.");
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_EMAIL, response);
    }

}