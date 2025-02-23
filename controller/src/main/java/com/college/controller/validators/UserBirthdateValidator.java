package com.college.controller.validators;

import com.college.model.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class UserBirthdateValidator implements UserValidator {
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private final Date lowestDate;

    public UserBirthdateValidator() {
        try {
            lowestDate = DATE_FORMAT.parse("01/01/1900");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserValidationResponse validate(User user) {
        if (user.getBirthday() == null)
            return UserValidationResponse.INVALID_BIRTHDATE;
        if (user.getBirthday().before(lowestDate))
            return UserValidationResponse.INVALID_BIRTHDATE;

        LocalDate userBirthDate = user.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();

        Period timeBetween = Period.between(userBirthDate, now);
        if (timeBetween.getYears() < 18) {
            return UserValidationResponse.INVALID_BIRTHDATE;
        }

        return UserValidationResponse.VALID;
    }
}
