package com.college.controller.validators;


import com.college.model.entity.User;

public class UserAddressValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (user.getAddress() == null || user.getAddress().trim().isEmpty())
            return UserValidationResponse.INVALID_ADDRESS;

        return UserValidationResponse.VALID;
    }
}
