package com.college.controller;

import com.college.model.database.interfaces.ContractDAO;
import com.college.model.entity.Contract;

import java.util.List;

public class ContractController {
    private final ContractDAO contractDAO;

    public ContractController(ContractDAO contractDAO) {
        this.contractDAO = contractDAO;
    }

    public Contract getContract(int id) {
        return contractDAO.getById(id);
    }

    public List<Contract> getAllContracts() {
        return contractDAO.getAll();
    }

    public boolean saveContract(Contract contract) {
        Contract savedContract = contractDAO.save(contract);
        return savedContract != null;
    }
}
