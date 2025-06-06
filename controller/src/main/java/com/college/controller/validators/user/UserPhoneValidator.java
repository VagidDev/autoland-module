package com.college.controller.validators.user;

import com.college.model.entity.User;


public class UserPhoneValidator implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {

        if (user.getTelephone() == null || user.getTelephone().isEmpty())
            return UserValidationResponse.INVALID_PHONE;

        if (user.getTelephone().length() != 12)
            return UserValidationResponse.INVALID_PHONE;

        if (!user.getTelephone().startsWith("+373"))
            return UserValidationResponse.INVALID_PHONE;

        return UserValidationResponse.VALID;
    }
}
