package com.college.controller.validators.user;

import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.User;


public class UserPhoneValidator extends EmptyStringValidator<User, UserValidationResponse> implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (super.validate(user) != getValidResponse())
            return getInvalidResponse();

        String regexp = "^\\+373\\d{8}$";
        if (!user.getTelephone().matches(regexp)) {
            return getInvalidResponse();
        }

        return getValidResponse();
    }

    @Override
    protected String getStringToValidate(User user) {
        return user.getTelephone();
    }

    @Override
    protected UserValidationResponse getValidResponse() {
        return UserValidationResponse.VALID;
    }

    @Override
    protected UserValidationResponse getInvalidResponse() {
        return UserValidationResponse.INVALID_PHONE;
    }
}
