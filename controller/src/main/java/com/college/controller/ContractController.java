package com.college.controller;

import com.college.model.database.interfaces.ContractDAO;
import com.college.model.entity.Contract;
import com.college.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Contract> getContractsByUser(User user) {
        return contractDAO.getAll().stream().
                filter(contract -> contract.getUser().getId() == user.getId())
                .collect(Collectors.toList());
    }

    public boolean saveContract(Contract contract) {
        Contract savedContract = contractDAO.save(contract);
        return savedContract != null;
    }
}
