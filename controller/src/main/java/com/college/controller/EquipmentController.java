/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.Automobile;
import com.college.model.Equipment;
import com.college.model.database.interfaces.EquipmentDAO;
import com.college.model.database.session.SessionManager;
import com.college.model.keys.EquipmentId;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Vagid Zibliuc
 */
public class EquipmentController {
    private final EquipmentDAO equipmentRepository;
    
    public EquipmentController() {
        equipmentRepository = SessionManager.getSession().getEquipmentRepository();
    }
    
    public List<Equipment> getAllEquipments() {
        return equipmentRepository.getAll();
    }
    
    public List<Equipment> getEquipmentsByAutomobile(Automobile auto) {
        return equipmentRepository.getByAuto(auto);
    }
    
    public Equipment getEquipmentById(int autoId, int equipId) {
        return equipmentRepository.getById(new EquipmentId(autoId, equipId));
    }
    
    public List<String> getEngineTypes() {
        return equipmentRepository.getEngineTypes();
    }
    
    public List<String> getSuspensionTypes() {
        return equipmentRepository.getSuspensionTypes();
    }
    
    public List<String> getDriveTypes(){
        return equipmentRepository.getDriveTypes();
    }
    
    public List<String> getGearboxTypes() {
        return equipmentRepository.getGearboxTypes();
    }
    
    public List<String> getFuelTypes() {
        return equipmentRepository.getFuelTypes();
    }
 
    private int setNewId(Automobile auto) {
        List<Equipment> equipments = equipmentRepository.getByAuto(auto);
        Optional<Integer> maxId = equipments.stream()
                .map(e -> e.getId())
                .max(Integer::compare);
        if(maxId.isPresent()) {
            return maxId.get() + 1;
        }
        return 1;
    }
    
    public boolean saveEquipment(Automobile auto, String name, String engineName, String engType, String engVol, String horsepower, String suspType, 
            String driveType, String gearboxType, String speedCount, String fuelType, String interior, String bodyKit, String weight, String price) {
        
        float processedVolume = Float.parseFloat(engVol);
        int processedHorsepower = Integer.parseInt(horsepower);
        int processedSpeedCount = Integer.parseInt(speedCount);
        int processedWeight = Integer.parseInt(weight);
        double processedPrice = Double.parseDouble(price);
        int newEquipId = setNewId(auto);
        
        Equipment equipment = new Equipment();
        
        equipment.setAutomobile(auto);
        equipment.setId(newEquipId);
        equipment.setName(name);
        equipment.setEngineName(engineName);
        equipment.setEngineType(engType);
        equipment.setEngineVolume(processedVolume);
        equipment.setHorsepower(processedHorsepower);
        equipment.setSuspensiveType(suspType);
        equipment.setDriveType(driveType);
        equipment.setGearboxType(gearboxType);
        equipment.setSpeedCount(processedSpeedCount);
        equipment.setFuelType(fuelType);
        equipment.setInterior(interior);
        equipment.setBodyKit(bodyKit);
        equipment.setWeigth(processedWeight);
        equipment.setPrice(processedPrice);
        
        Equipment newEquipment = equipmentRepository.save(equipment);
        return newEquipment != null;
    }
    
        public boolean updateEquipment(Equipment equipment, String name, String engineName, String engType, String engVol, String horsepower, String suspType, 
            String driveType, String gearboxType, String speedCount, String fuelType, String interior, String bodyKit, String weight, String price) {
        
        float processedVolume = Float.parseFloat(engVol);
        int processedHorsepower = Integer.parseInt(horsepower);
        int processedSpeedCount = Integer.parseInt(speedCount);
        int processedWeight = Integer.parseInt(weight);
        double processedPrice = Double.parseDouble(price);
        
        equipment.setAutomobile(equipment.getAutomobile());
        equipment.setId(equipment.getId());
        equipment.setName(name);
        equipment.setEngineName(engineName);
        equipment.setEngineType(engType);
        equipment.setEngineVolume(processedVolume);
        equipment.setHorsepower(processedHorsepower);
        equipment.setSuspensiveType(suspType);
        equipment.setDriveType(driveType);
        equipment.setGearboxType(gearboxType);
        equipment.setSpeedCount(processedSpeedCount);
        equipment.setFuelType(fuelType);
        equipment.setInterior(interior);
        equipment.setBodyKit(bodyKit);
        equipment.setWeigth(processedWeight);
        equipment.setPrice(processedPrice);
        
        return equipmentRepository.update(equipment);
    }
    
    public boolean deleteEquipment(int autoId, int equipId) {
        return equipmentRepository.deleteByID(new EquipmentId(autoId, equipId));
    }
}
