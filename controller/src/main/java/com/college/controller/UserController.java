package com.college.controller;

import com.college.controller.validators.UserLoginSyntaxValidator;
import com.college.controller.validators.UserValidationResponse;
import com.college.controller.validators.UserValidator;
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
        validatorList.add(new UserLoginSyntaxValidator());
    }

    public List<User> getUsers() {
        return userDAO.getAll();
    }

    public User getUser(int id) {
        return userDAO.getById(id);
    }

    private boolean validateUser(User user) {
        for (UserValidator validator : validatorList) {
            if (validator.validate(user) != UserValidationResponse.VALID) {
                return false;
            }
        }
        return true;
    }

    public User createUser(User user) {
        if (!validateUser(user)) {
            return null;
        }
        return userDAO.save(user);
    }

}
