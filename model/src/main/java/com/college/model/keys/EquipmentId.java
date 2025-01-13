/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.keys;

import com.college.model.Automobile;

import java.util.Objects;

/**
 *
 * @author Vagid Zibliuc
 */
public class EquipmentId {
    private final Automobile automobile;
    private final int equipmentId;

    public EquipmentId(Automobile automobile, int equipmentId) {
        this.automobile = automobile;
        this.equipmentId = equipmentId;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public int getEquipmentId() {
        return equipmentId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentId that = (EquipmentId) o;
        return automobile.getId() == that.automobile.getId() && equipmentId == that.equipmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(automobile.getId(), equipmentId);
    }
}
