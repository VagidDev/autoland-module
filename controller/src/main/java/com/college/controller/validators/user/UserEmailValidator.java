package com.college.controller.validators.user;

import com.college.controller.validators.EmailValidator;
import com.college.model.entity.User;

public class UserEmailValidator extends EmailValidator<User, UserValidationResponse> implements UserValidator {

    @Override
    protected String getStringToValidate(User user) {
        return user.getEmail();
    }

    @Override
    protected UserValidationResponse getValidResponse() {
        return UserValidationResponse.VALID;
    }

    @Override
    protected UserValidationResponse getInvalidResponse() {
        return UserValidationResponse.INVALID_EMAIL;
    }
}
