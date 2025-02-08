package com.college.controller.validators;

import com.college.model.User;

public interface UserValidator {
    UserValidationResponse validate(User user);
}
