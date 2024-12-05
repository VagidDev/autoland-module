/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.Warranty;
import com.college.model.database.interfaces.WarrantyDAO;
import com.college.model.database.session.SessionManager;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class WarrantyController {
    private final WarrantyDAO warrantyRepository;
    
    public WarrantyController() {
        warrantyRepository = SessionManager.getSession().getWarrantyRepository();
    }
    
    public List<Warranty> getAllWarranties() {
        return warrantyRepository.getAll();
    }
}
