package com.college.controller.validators.user;

import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.User;

public class UserPasswordValidator extends EmptyStringValidator<User, UserValidationResponse> implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {

        if (super.validate(user) != getValidResponse()) {
            return getInvalidResponse();
        }

        String regexp = "^[a-zA-Z0-9_.!@#$%^&*]{8,30}$";
        if (!user.getPassword().matches(regexp)) {
            return UserValidationResponse.INVALID_PASSWORD;
        }
        return UserValidationResponse.VALID;
    }

    @Override
    protected String getStringToValidate(User user) {
        return user.getPassword();
    }

    @Override
    protected UserValidationResponse getValidResponse() {
        return UserValidationResponse.VALID;
    }

    @Override
    protected UserValidationResponse getInvalidResponse() {
        return UserValidationResponse.INVALID_PASSWORD;
    }
}
