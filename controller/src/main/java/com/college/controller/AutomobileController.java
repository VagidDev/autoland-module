/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.controller;

import com.college.model.Automobile;
import com.college.model.Equipment;
import com.college.model.database.interfaces.AutomobileDAO;
import com.college.model.database.interfaces.EquipmentDAO;
import com.college.model.database.session.SessionManager;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class AutomobileController {
    private final AutomobileDAO automobileRepository;
    private final EquipmentDAO equipmentRepository;
    
    public AutomobileController() {
        automobileRepository = SessionManager.getSession().getAutomobileRepository();
        equipmentRepository = SessionManager.getSession().getEquipmentRepository();
    }
    
    public List<Automobile> getAllAutomobiles() {
        return automobileRepository.getAll();
    }
    
    public Automobile getAutoById(int id) {
        return automobileRepository.getById(id);
    }
    
    public List<Equipment> getEquipmentsByAutomobile(Automobile auto) {
        return equipmentRepository.getByAuto(auto);
    }
}
