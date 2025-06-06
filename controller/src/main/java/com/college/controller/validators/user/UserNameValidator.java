package com.college.controller.validators.user;

import com.college.model.entity.User;

public class UserNameValidator implements UserValidator{
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty())
            return UserValidationResponse.INVALID_NAME;

        return UserValidationResponse.VALID;
    }
}
