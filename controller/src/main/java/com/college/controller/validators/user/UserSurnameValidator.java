package com.college.controller.validators.user;

import com.college.model.entity.User;


public class UserSurnameValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getSurname() == null || user.getSurname().trim().isEmpty())
            return UserValidationResponse.INVALID_SURNAME;

        return UserValidationResponse.VALID;
    }
}
