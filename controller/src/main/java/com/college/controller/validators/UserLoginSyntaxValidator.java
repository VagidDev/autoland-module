package com.college.controller.validators;


import com.college.model.User;

import java.util.regex.Pattern;

public class UserLoginSyntaxValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getLogin() == null)
            return UserValidationResponse.INVALID_LOGIN;

        String regexp = "^[a-zA-Z0-9_]{3,20}$";
        if (!user.getLogin().matches(regexp))
            return UserValidationResponse.INVALID_LOGIN;

        return UserValidationResponse.VALID;
    }
}
