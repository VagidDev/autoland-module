package com.college.controller.validators;

import com.college.model.User;

public class UserNameValidator implements UserValidator{
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty())
            return UserValidationResponse.INVALID_NAME;

        return UserValidationResponse.VALID;
    }
}
