package com.college.controller.validators.user;

import com.college.model.entity.User;

public interface UserValidator {
    UserValidationResponse validate(User user);
}
