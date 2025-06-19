package com.college.controller;

import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.interfaces.EquipmentDAO;
import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.entity.keys.EquipmentId;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

        if (!equipment.getImagePath().isEmpty()) {
            String newPath = savEquipmentImage(equipment);
            equipment.setImagePath(newPath);
        }

        return equipmentDAO.save(equipment);
    }

    public boolean editEquipment(Equipment equipment) {
        if (equipment == null) {
            return false;
        }

        if (!equipment.getImagePath().isEmpty()) {
            String newPath = savEquipmentImage(equipment);
            equipment.setImagePath(newPath);
        }

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

    private String savEquipmentImage(Equipment equipment) {
        String outputPath = "view/src/main/resources/automobiles/"
                + equipment.getAutomobile().getMark() + equipment.getAutomobile().getModel() + equipment.getId().getEquipmentId() + ".jpg";
        File outputFile = new File(outputPath);
        try {
            Files.copy(Paths.get(new URI(equipment.getImagePath())), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return outputFile.toURI().toString();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
