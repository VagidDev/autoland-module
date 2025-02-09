package com.college.controller.validators;

import com.college.model.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserBirthdateValidatorTest {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final UserBirthdateValidator validator = new UserBirthdateValidator();

    @Test
    void shouldValidateUserBirthdate() throws ParseException {
        Date birthdate = dateFormat.parse("01/01/2005");
        User user = new User();
        user.setBirthday(birthdate);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.VALID, response);
    }

    @Test
    void shouldNotBeValid_Null() {
        User user = new User();
        user.setBirthday(null);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_BIRTHDATE, response);
    }

    @Test
    void shouldNotBeValid_tooYoung() throws ParseException {
        Date birthdate = dateFormat.parse("01/01/2013");
        User user = new User();
        user.setBirthday(birthdate);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_BIRTHDATE, response);
    }

    @Test
    void shouldNotBeValid_tooOld() throws ParseException {
        Date birthdate = dateFormat.parse("01/01/1800");
        User user = new User();
        user.setBirthday(birthdate);
        UserValidationResponse response = validator.validate(user);
        assertEquals(UserValidationResponse.INVALID_BIRTHDATE, response);
    }

}