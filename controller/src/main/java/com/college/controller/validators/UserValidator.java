package com.college.controller.validators;

import com.college.model.entity.User;

public interface UserValidator {
    UserValidationResponse validate(User user);
}
