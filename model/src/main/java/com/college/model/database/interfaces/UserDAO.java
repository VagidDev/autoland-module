/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.college.model.database.interfaces;

import com.college.model.entity.User;

/**
 *
 * @author Vagid Zibliuc
 */
public interface UserDAO extends CRUDRepository<Integer, User> {
    User getByLoginAndPassword(String login, String password);
    boolean ifUserExists(String login);
}
