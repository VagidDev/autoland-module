package com.college.controller.validators;

import com.college.model.User;

public class UserPasswordValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getPassword() == null)
            return UserValidationResponse.INVALID_PASSWORD;

        String regexp = "^[a-zA-Z0-9_.]{8,30}$";
        if (!user.getPassword().matches(regexp)) {
            return UserValidationResponse.INVALID_PASSWORD;
        }
        return UserValidationResponse.VALID;
    }
}
