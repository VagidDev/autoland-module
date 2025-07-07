package com.college.controller.validators.user;


import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.User;

public class UserAddressValidator extends EmptyStringValidator<User, UserValidationResponse> implements UserValidator  {

    @Override
    protected String getStringToValidate(User user) {
        return user.getAddress();
    }

    @Override
    protected UserValidationResponse getValidResponse() {
        return UserValidationResponse.VALID;
    }

    @Override
    protected UserValidationResponse getInvalidResponse() {
        return UserValidationResponse.INVALID_ADDRESS;
    }
}
