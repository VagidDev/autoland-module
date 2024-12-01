/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.session;

/**
 *
 * @author Vagid Zibliuc
 */
public final class SessionManager {
    private static final Session session = new Session();
    public static Session getSession() {
        return session;
    }
    
    private SessionManager(){}
}
