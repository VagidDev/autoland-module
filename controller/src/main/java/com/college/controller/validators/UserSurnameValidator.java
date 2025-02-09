package com.college.controller.validators;

import com.college.model.User;


public class UserSurnameValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getSurname() == null || user.getSurname().trim().isEmpty())
            return UserValidationResponse.INVALID_SURNAME;

        return UserValidationResponse.VALID;
    }
}
