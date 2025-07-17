package com.college.controller.validators.user;

import com.college.controller.validators.PhoneNumberValidator;
import com.college.model.entity.User;


public class UserPhoneValidator extends PhoneNumberValidator<User, UserValidationResponse> implements UserValidator {

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
