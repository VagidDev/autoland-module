package com.college.controller;

import com.college.model.entity.User;
import com.college.model.database.interfaces.UserDAO;

public class AuthorizationController {
    private final UserDAO userDAO;
    private User currentUser;

    public AuthorizationController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String username, String password) {
        this.currentUser = userDAO.getByLoginAndPassword(username, password);
        return this.currentUser != null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void editCurrentUserInfo(User user) {
        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setBirthday(user.getBirthday());
        currentUser.setEmail(user.getEmail());
        currentUser.setTelephone(user.getTelephone());
        currentUser.setAddress(user.getAddress());
    }

    public void logout() {
        this.currentUser = null;
    }
}
