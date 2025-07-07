package com.college.controller.validators.user;

import com.college.controller.validators.DateFormatValidator;
import com.college.model.entity.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class UserBirthdateValidator extends DateFormatValidator<User, UserValidationResponse> implements UserValidator {

    @Override
    public UserValidationResponse validate(User user) {
        UserValidationResponse response = super.validate(user);
        if (response != getValidResponse()) {
            return response;
        }

        LocalDate userBirthDate = user.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        Period timeBetween = Period.between(userBirthDate, now);
        if (timeBetween.getYears() < 18) {
            return UserValidationResponse.INVALID_BIRTHDATE;
        }
        return UserValidationResponse.VALID;
    }

    @Override
    protected Date getDate(User user) {
        return user.getBirthday();
    }

    @Override
    protected UserValidationResponse getValidResponse() {
        return UserValidationResponse.VALID;
    }

    @Override
    protected UserValidationResponse getInvalidResponse() {
        return UserValidationResponse.INVALID_BIRTHDATE;
    }
}
