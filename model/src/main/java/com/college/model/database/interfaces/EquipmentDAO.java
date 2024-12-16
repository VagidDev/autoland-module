/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.college.model.database.interfaces;

import com.college.model.Automobile;
import com.college.model.Equipment;
import com.college.model.keys.EquipmentId;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public interface EquipmentDAO extends CRUDRepository<EquipmentId, Equipment> {
    List<Equipment> getByAuto(Automobile automobile);
}
