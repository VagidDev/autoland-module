package com.college.controller.validators;

import com.college.model.entity.User;

public class UserEmailValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getEmail() == null)
            return UserValidationResponse.INVALID_EMAIL;

        if (user.getEmail().isEmpty() || !user.getEmail().endsWith("@gmail.com")) {
            return UserValidationResponse.INVALID_EMAIL;
        }

        return UserValidationResponse.VALID;
    }
}
