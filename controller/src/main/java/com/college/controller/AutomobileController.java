/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.Automobile;
import com.college.model.database.interfaces.AutomobileDAO;
import com.college.model.database.session.SessionManager;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class AutomobileController {
    private final AutomobileDAO automobileRepository;
    
    public AutomobileController() {
        automobileRepository = SessionManager.getSession().getAutomobileRepository();
    }
    
    public List<Automobile> getAllAutomobiles() {
        return automobileRepository.getAll();
    }
    
    public Automobile getAutoById(int id) {
        return automobileRepository.getById(id);
    }
    
}
