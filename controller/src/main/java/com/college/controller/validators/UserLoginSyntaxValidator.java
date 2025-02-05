package com.college.controller.validators;


import com.college.model.User;

import java.util.regex.Pattern;

public class UserLoginSyntaxValidator implements UserValidator {
    @Override
    public boolean validate(User user) {
        String regexp = "^[a-zA-Z0-9_]{3,20}$";
        return Pattern.matches(regexp, user.getLogin());
    }
}
