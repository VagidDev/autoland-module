package com.college.controller.validators;


import com.college.model.User;

import java.util.regex.Pattern;

public class UserLoginSyntaxValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        String regexp = "^[a-zA-Z0-9_]{3,20}$";
        if (Pattern.matches(regexp, user.getLogin()))
            return UserValidationResponse.VALID;
        return UserValidationResponse.INVALID_LOGIN;
    }
}
