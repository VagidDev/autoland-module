/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.User;
import com.college.model.database.interfaces.UserDAO;
import com.college.model.database.session.SessionManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class UserController {
    private final UserDAO userRepository;
    private final static SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
        
    private User loginUser;
    
    public UserController() {
        this.userRepository = SessionManager.getSession().getUserRepository();
    }
       
    public List<User> getUsers() {
        return userRepository.getAll();
    }
    
    public User getUserById(int id) {
        return userRepository.getById(id);
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
    
    public boolean saveUser(String login, String password, String name, String surname, String birthday, String email, String telephone, String address) {
        Date processedDate;
        try {
            processedDate = smp.parse(birthday);
        } catch (ParseException ex) {
            return false;
        }
        if (!email.endsWith("@gmail.com")) {
            return false;
        }
        
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthday(processedDate);
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setAddress(address);
        
        User newUser = userRepository.save(user);
        
        return newUser != null;
    }
    
    public boolean deleteUserById(int id) {
        return userRepository.deleteByID(id);
    }
    
}
