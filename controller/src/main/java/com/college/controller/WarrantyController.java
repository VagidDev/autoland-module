package com.college.controller;

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
}
