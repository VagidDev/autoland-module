package com.college.controller.validators.user;

import com.college.controller.validators.Validator;
import com.college.model.entity.User;

public interface UserValidator extends Validator<User, UserValidationResponse> {
    //TODO: change interface to abstract class (optional), write tests for validators
    UserValidationResponse validate(User user);
}
