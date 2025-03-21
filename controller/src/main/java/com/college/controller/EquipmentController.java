package com.college.controller;

import com.college.model.database.interfaces.EquipmentDAO;
import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.entity.keys.EquipmentId;

import java.util.List;
import java.util.stream.Collectors;

public class EquipmentController {
    private final EquipmentDAO equipmentDAO;

    public EquipmentController(EquipmentDAO equipmentDAO) {
        this.equipmentDAO = equipmentDAO;
    }

    public Equipment getEquipment(EquipmentId id) {
        return equipmentDAO.getById(id);
    }

    public List<Equipment> getEquipmentByAutomobile(Automobile automobile) {
        return equipmentDAO.getByAuto(automobile);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentDAO.getAll();
    }

    public List<Equipment> getCheapestEquipmentForAutomobile() {
        return getAllEquipments()
                .stream()
                .filter(equipment -> equipment.getId().getEquipmentId() == 1)
                .collect(Collectors.toList());
    }

}
