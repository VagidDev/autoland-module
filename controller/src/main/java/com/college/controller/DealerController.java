package com.college.controller;

import com.college.model.Dealer;
import com.college.model.database.interfaces.DealerDAO;

import java.util.List;

public class DealerController {
    private final DealerDAO dealerDAO;

    public DealerController(DealerDAO dealerDAO) {
        this.dealerDAO = dealerDAO;
    }

    public List<Dealer> getAllDealers() {
        return dealerDAO.getAll();
    }

    public Dealer getDealerById(int id) {
        return dealerDAO.getById(id);
    }

}
