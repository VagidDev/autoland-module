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
    
    public Warranty getWarrantyById(int id) {
        return warrantyRepository.getById(id);
    }
    
    public boolean saveWarranty(String name, String duration, String price) {
        int parsedDuration = Integer.parseInt(duration);
        double parsedPrice = Double.parseDouble(price);
        
        Warranty warranty = new Warranty();
        warranty.setName(name);
        warranty.setDuration(parsedDuration);
        warranty.setPrice(parsedPrice);
        
        Warranty savedWarranty = warrantyRepository.save(warranty);
        return savedWarranty != null;   
    }
    
    public boolean updateWarranty(Warranty warranty, String name, String duration, String price) {
        int parsedDuraction = Integer.parseInt(duration);
        double parsedPrice = Double.parseDouble(price);
        
        warranty.setName(name);
        warranty.setDuration(parsedDuraction);
        warranty.setPrice(parsedPrice);
        
        return warrantyRepository.update(warranty);
    }
    
    public boolean deleteWarranty(int id) {
        return warrantyRepository.deleteByID(id);
    }
    
}
