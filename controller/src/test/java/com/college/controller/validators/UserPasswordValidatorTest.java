package com.college.controller.validators;

import com.college.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPasswordValidatorTest {
    private final UserPasswordValidator validator = new UserPasswordValidator();

    @Test
    void passwordShouldBeValid() {
        User user = new User();
        user.setPassword("qwerty123");
        UserValidationResponse res = validator.validate(user);
        assertEquals(UserValidationResponse.VALID, res);
    }

    @Test
    void passwordShouldBeValidWithCamelCase() {
        User user = new User();
        user.setPassword("cAmElCaSe_WoRkInG");
        UserValidationResponse res = validator.validate(user);
        assertEquals(UserValidationResponse.VALID, res);
    }

    @Test
    void passwordShouldNotBeValidBecauseOfPlus() {
        User user = new User();
        user.setPassword("qwe+rty123");
        UserValidationResponse res = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_PASSWORD, res);
    }

    @Test
    void passwordShouldNotBeValidBecauseOfBrackets() {
        User user = new User();
        user.setPassword("qwe[]rty123");
        UserValidationResponse res = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_PASSWORD, res);
    }

    @Test
    void passwordShouldNotBeValidBecauseOfLackSymbols() {
        User user = new User();
        user.setPassword("qwe");
        UserValidationResponse res = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_PASSWORD, res);
    }

}