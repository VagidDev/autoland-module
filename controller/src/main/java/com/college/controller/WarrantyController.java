package com.college.controller;

import com.college.controller.validators.warranty.*;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.interfaces.WarrantyDAO;
import com.college.model.entity.Warranty;

import java.util.ArrayList;
import java.util.List;

public class WarrantyController {
    private final WarrantyDAO warrantyDAO;
    private final List<WarrantyValidator>  warrantyValidators;

    public WarrantyController(WarrantyDAO warrantyDAO) {
        this.warrantyDAO = warrantyDAO;
        warrantyValidators = new ArrayList<>();

        warrantyValidators.add(new WarrantyNameValidator());
        warrantyValidators.add(new WarrantyDurationValidation());
        warrantyValidators.add(new WarrantyPriceValidator());
    }

    public List<Warranty> getAllWarranty() {
        return warrantyDAO.getAll();
    }

    public Warranty getWarranty(int id) {
        return warrantyDAO.getById(id);
    }

    public WarrantyValidationResponse validateWarranty(Warranty warranty) {
        for (WarrantyValidator warrantyValidator : warrantyValidators) {
            WarrantyValidationResponse response = warrantyValidator.validate(warranty);
            if (response != WarrantyValidationResponse.VALID) {
                return response;
            }
        }
        return WarrantyValidationResponse.VALID;
    }

    public Warranty createWarranty(Warranty warranty) {
        if (warranty == null) {
            return null;
        }

        return warrantyDAO.save(warranty);
    }

    public boolean editWarranty(Warranty warranty) {
        if (warranty == null) {
            return false;
        }

        warrantyDAO.update(warranty);
        return true;
    }

    public void deleteWarranty(Warranty warranty) throws CascadeDependencyException {
        if (warranty == null) {
            return;
        }

        if (warrantyDAO.getById(warranty.getId()) != null) {
            warrantyDAO.delete(warranty);
        }
    }
}
