package com.college.controller.validators;

import com.college.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserLoginSyntaxValidatorTest {

    private final UserLoginSyntaxValidator loginSyntaxValidator = new UserLoginSyntaxValidator();

    @Test
    void shouldBeValid() {
        User user = new User();
        user.setLogin("admin");
        UserValidationResponse res = loginSyntaxValidator.validate(user);
        Assertions.assertEquals(UserValidationResponse.VALID, res);
    }

    @Test
    void shouldNotBeValid() {
        User user = new User();
        user.setLogin("admin{mnsd/");
        UserValidationResponse res = loginSyntaxValidator.validate(user);
        Assertions.assertEquals(UserValidationResponse.INVALID_LOGIN, res);
    }

    @Test
    void shouldNotBeValidBecauseOfNull() {
        User user = new User();
        user.setLogin(null);
        UserValidationResponse res = loginSyntaxValidator.validate(user);
        Assertions.assertEquals(UserValidationResponse.INVALID_LOGIN, res);
    }

    @Test
    void shouldNotBeValidBecauseOfTooShort() {
        User user = new User();
        user.setLogin("12");
        UserValidationResponse res = loginSyntaxValidator.validate(user);
        Assertions.assertEquals(UserValidationResponse.INVALID_LOGIN, res);
    }

}