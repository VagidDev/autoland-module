package com.college.controller;

import com.college.controller.core.AppConfig;
import com.college.controller.core.ImageSaver;
import com.college.model.database.exceptions.CascadeDependencyException;
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

    public List<Equipment> searchAutomobile(String keyword) {
        String keywordLower = keyword.trim().toLowerCase();
        return equipmentDAO.searchByModelAndMark(keywordLower);
    }

    public Equipment createEquipment(Equipment equipment) {
        if (equipment == null) {
            return null;
        }

        setEquipmentImage(equipment);

        return equipmentDAO.save(equipment);
    }

    public boolean editEquipment(Equipment equipment) {
        if (equipment == null) {
            return false;
        }
        setEquipmentImage(equipment);
        equipmentDAO.update(equipment);
        return true;
    }

    public void deleteEquipment(Equipment equipment) throws CascadeDependencyException {
        if (equipment == null) {
            return;
        }

        if (equipmentDAO.getById(equipment.getId()) != null) {
            equipmentDAO.delete(equipment);
        }
    }

    private void setEquipmentImage(Equipment equipment) {
        if (!equipment.getImagePath().isEmpty()) {
            String originalImagePath = equipment.getImagePath();
            int formatDotPosition = originalImagePath.lastIndexOf(".");
            String format = originalImagePath.substring(formatDotPosition);
            String newImageName = equipment.getAutomobile().getMark() + equipment.getAutomobile().getModel() + equipment.getEngineName();
            String newImagePath = AppConfig.get("photo.automobiles");

            String newPath = ImageSaver.copyImageToDirectory(originalImagePath, newImagePath, newImageName, format);
            equipment.setImagePath(newPath);
        }
    }
}
