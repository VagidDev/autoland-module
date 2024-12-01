/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.session;

import com.college.model.database.implementations.*;
import com.college.model.database.interfaces.*;

/**
 *
 * @author Vagid Zibliuc
 */
public class Session {

    private final UserDAO userRepository;
    private final DealerDAO dealerRepository;
    private final WarrantyDAO warrantyRepository;
    private final AutomobileDAO automobileRepository;
    private final EquipmentDAO equipmentRepository;
    private final ContractDAO contractRepository;

    public Session() {
        this.userRepository = new UserImpl();
        this.dealerRepository = new DealerImpl();
        this.warrantyRepository = new WarrantyImpl();
        this.automobileRepository = new AutomobileImpl();
        this.equipmentRepository = new EquipmentImpl(automobileRepository);
        this.contractRepository = new ContractImpl(userRepository, dealerRepository, warrantyRepository, automobileRepository, equipmentRepository);
    }

    public UserDAO getUserRepository() {
        return userRepository;
    }

    public DealerDAO getDealerRepository() {
        return dealerRepository;
    }

    public WarrantyDAO getWarrantyRepository() {
        return warrantyRepository;
    }

    public AutomobileDAO getAutomobileRepository() {
        return automobileRepository;
    }

    public EquipmentDAO getEquipmentRepository() {
        return equipmentRepository;
    }

    public ContractDAO getContractRepository() {
        return contractRepository;
    }
    
}
