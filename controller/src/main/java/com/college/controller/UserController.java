/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.User;
import com.college.model.database.interfaces.UserDAO;
import com.college.model.database.session.SessionManager;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class UserController {
    private final UserDAO userRepository;
        
    private User loginUser;
    
    public UserController() {
        this.userRepository = SessionManager.getSession().getUserRepository();
    }
       
    public List<User> getUsers() {
        return userRepository.getAll();
    }
    
    public boolean authentificateUser(String login, String password) {
        User user = userRepository.getByLoginAndPassword(login, password);
        if (user == null) {
            return false;
        } else {
            loginUser = user;
            return true;
        }
    }
    
    public User getAuthentificatedUser() {
        return loginUser;
    }
    
}
