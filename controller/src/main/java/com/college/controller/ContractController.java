/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.Contract;
import com.college.model.database.interfaces.ContractDAO;
import com.college.model.database.interfaces.WarrantyDAO;
import com.college.model.database.session.SessionManager;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class ContractController {
    private final ContractDAO contractRepository;
    private final WarrantyDAO warrantyRepository;

    public ContractController() {
        this.contractRepository = SessionManager.getSession().getContractRepository();
        this.warrantyRepository = SessionManager.getSession().getWarrantyRepository();
    }
    
    public List<Contract> getAllContracts() {
        return contractRepository.getAll();
    }
    
    public Contract getContractById(int id) {
        return contractRepository.getById(id);
    }
    
    public boolean registenNewContract(Contract contract) {
        Contract newContract = contractRepository.save(contract);
        return newContract != null;
    }
    public boolean registenNewContractWithWarranty(Contract contract) {
        contract.setWarranty(warrantyRepository.getById(1));
        Contract newContract = contractRepository.save(contract);
        return newContract != null;
    }
    
    public boolean deleteContract(int id) {
        return contractRepository.deleteByID(id);
    }
}
