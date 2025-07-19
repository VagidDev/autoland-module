package com.college.controller;

import com.college.controller.validators.dealer.*;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.Dealer;
import com.college.model.database.interfaces.DealerDAO;

import java.util.ArrayList;
import java.util.List;

public class DealerController {
    private final DealerDAO dealerDAO;
    private final List<DealerValidator> dealerValidators;

    public DealerController(DealerDAO dealerDAO) {
        this.dealerDAO = dealerDAO;
        dealerValidators = new ArrayList<>();

        dealerValidators.add(new DealerNameValidator());
        dealerValidators.add(new DealerAddressValidator());
        dealerValidators.add(new DealerTelephoneValidator());
        dealerValidators.add(new DealerFaxValidator());
    }

    public List<Dealer> getAllDealers() {
        return dealerDAO.getAll();
    }

    public Dealer getDealerById(int id) {
        return dealerDAO.getById(id);
    }

    public DealerValidationResponse validateDealer(Dealer dealer) {
        for (DealerValidator validator : dealerValidators) {
            DealerValidationResponse response = validator.validate(dealer);
            if (response != DealerValidationResponse.VALID) {
                return response;
            }
        }
        return DealerValidationResponse.VALID;
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
