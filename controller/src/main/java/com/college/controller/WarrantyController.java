package com.college.controller;

import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.interfaces.WarrantyDAO;
import com.college.model.entity.Warranty;

import java.util.List;

public class WarrantyController {
    private final WarrantyDAO warrantyDAO;

    public WarrantyController(WarrantyDAO warrantyDAO) {
        this.warrantyDAO = warrantyDAO;
    }

    public List<Warranty> getAllWarranty() {
        return warrantyDAO.getAll();
    }

    public Warranty getWarranty(int id) {
        return warrantyDAO.getById(id);
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
