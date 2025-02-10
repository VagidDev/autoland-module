package com.college.controller;

import com.college.controller.validators.*;
import com.college.model.User;
import com.college.model.database.interfaces.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final UserDAO userDAO;
    private final List<UserValidator> validatorList;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.validatorList = new ArrayList<>();
        /*user validation criteria*/
        validatorList.add(new UserLoginSyntaxValidator());
        validatorList.add(new UserPasswordValidator());
        validatorList.add(new UserNameValidator());
        validatorList.add(new UserSurnameValidator());
        validatorList.add(new UserBirthdateValidator());
        validatorList.add(new UserEmailValidator());
        validatorList.add(new UserPhoneValidator());
        validatorList.add(new UserAddressValidator());
    }

    public List<User> getUsers() {
        return userDAO.getAll();
    }

    public User getUser(int id) {
        return userDAO.getById(id);
    }

    public UserValidationResponse validateUser(User user) {
        for (UserValidator validator : validatorList) {
            UserValidationResponse response = validator.validate(user);
            if (response != UserValidationResponse.VALID) {
                return response;
            }
        }
        return UserValidationResponse.VALID;
    }

    public User createUser(User user) {
        if (validateUser(user) != UserValidationResponse.VALID) {
            return null;
        }
        return userDAO.save(user);
    }

}
