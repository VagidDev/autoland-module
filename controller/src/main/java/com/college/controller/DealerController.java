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
    
    public Dealer getDealerById(int id) {
        return dealerRepository.getById(id);
    }
    
    public boolean saveDealer(String name, String address, String telephone, String fax) {
        Dealer dealer = new Dealer();
        dealer.setName(name);
        dealer.setAddress(address);
        dealer.setTelephone(telephone);
        dealer.setFax(fax);
        Dealer savedDealer = dealerRepository.save(dealer);
        return savedDealer != null;
    }
    
    public boolean updateDealer(Dealer dealer, String name, String address, String telephone, String fax) {
        dealer.setName(name);
        dealer.setAddress(address);
        dealer.setTelephone(telephone);
        dealer.setFax(fax);
        return dealerRepository.update(dealer);
    }
    
    public boolean deleteDealer(int id) {
        return dealerRepository.deleteByID(id);
    }
}
