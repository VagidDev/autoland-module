package com.college.controller.validators.user;


import com.college.controller.validators.EmptyStringValidator;
import com.college.model.database.SessionManager;
import com.college.model.database.implementations.UserImpl;
import com.college.model.database.interfaces.UserDAO;
import com.college.model.entity.User;

public class UserLoginSyntaxValidator extends EmptyStringValidator<User, UserValidationResponse> implements UserValidator {
    @Override
    public UserValidationResponse validate(User user) {
        if (super.validate(user) != UserValidationResponse.VALID) {
            return getInvalidResponse();
        }

        String regexp = "^[a-zA-Z0-9_]{3,20}$";
        if (!user.getLogin().matches(regexp))
            return getInvalidResponse();

        return checkIsUnique(user.getLogin());
    }

    @Override
    protected String getStringToValidate(User user) {
        return user.getLogin();
    }

    @Override
    protected UserValidationResponse getValidResponse() {
        return UserValidationResponse.VALID;
    }

    @Override
    protected UserValidationResponse getInvalidResponse() {
        return UserValidationResponse.INVALID_LOGIN;
    }

    private UserValidationResponse checkIsUnique(String login) {
        UserDAO userDAO = new UserImpl();
        if (userDAO.ifUserExists(login))
            return UserValidationResponse.LOGIN_EXISTS;
        return getValidResponse();
    }
}
