package com.college.controller;

import com.college.model.User;
import com.college.model.database.interfaces.UserDAO;

public class UserController {
    private final UserDAO userDAO;
    private User currentUser;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) {
        this.currentUser = userDAO.getByLoginAndPassword(username, password);
        return this.currentUser != null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }



}
