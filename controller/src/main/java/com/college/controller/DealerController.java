/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.Dealer;
import com.college.model.database.implementations.DealerImpl;
import com.college.model.database.interfaces.DealerDAO;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class DealerController {
    private final DealerDAO dealerRepository;
        
    public DealerController() {
        dealerRepository = new DealerImpl();
    }
    
    public List<Dealer> getAllDealers() {
        return dealerRepository.getAll();
    }
    
    
}
