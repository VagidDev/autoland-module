package com.college.controller;

import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.Dealer;
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

    public Dealer createDealer(Dealer dealer) {
        if (dealer == null) {
            return null;
        }

        return dealerDAO.save(dealer);
    }

    public boolean editDealer(Dealer dealer) {
        if (dealer == null) {
            return false;
        }

        dealerDAO.update(dealer);
        return true;
    }

    public void deleteDealer(Dealer dealer) throws CascadeDependencyException {
        if (dealer == null) {
            return;
        }

        if (dealerDAO.getById(dealer.getId()) != null) {
            dealerDAO.delete(dealer);
        }
    }

}
