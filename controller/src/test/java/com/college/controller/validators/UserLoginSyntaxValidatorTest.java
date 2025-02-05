package com.college.controller.validators;

import com.college.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserLoginSyntaxValidatorTest {

    private final UserLoginSyntaxValidator loginSyntaxValidator = new UserLoginSyntaxValidator();

    @Test
    void shouldBeValid() {
        User user = new User();
        user.setLogin("admin");
        boolean res = loginSyntaxValidator.validate(user);
        Assertions.assertTrue(res);
    }

    @Test
    void shouldNotBeValid() {
        User user = new User();
        user.setLogin("admin{mnsd/");
        boolean res = loginSyntaxValidator.validate(user);
        Assertions.assertFalse(res);
    }
}