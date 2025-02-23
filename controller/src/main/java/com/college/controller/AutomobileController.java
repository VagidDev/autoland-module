package com.college.controller;

import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.database.interfaces.AutomobileDAO;
import com.college.model.database.interfaces.EquipmentDAO;

import java.util.List;

public class AutomobileController {
    private final AutomobileDAO automobileDAO;
    private final EquipmentDAO equipmentDAO;

    public AutomobileController(AutomobileDAO automobileDAO, EquipmentDAO equipmentDAO) {
        this.automobileDAO = automobileDAO;
        this.equipmentDAO = equipmentDAO;
    }

    public List<Automobile> getAllAutomobiles() {
        return automobileDAO.getAll();
    }

    public Automobile getAutomobileById(int id) {
        return automobileDAO.getById(id);
    }

    public List<Equipment> getEquipmentsByAutomobileId(int id) {
        return equipmentDAO.getByAuto(getAutomobileById(id));
    }

    public List<Equipment> getEquipmentsByAutomobile(Automobile automobile) {
        return equipmentDAO.getByAuto(automobile);
    }

}
