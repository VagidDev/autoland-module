/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.keys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Vagid Zibliuc
 */
@Embeddable
public class EquipmentId implements Serializable {
    private int automobileId;
    private int equipmentId;

    public EquipmentId() {}

    public EquipmentId(int automobileId, int equipmentId) {
        this.automobileId = automobileId;
        this.equipmentId = equipmentId;
    }

    public int getAutomobileId() {
        return automobileId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentId that = (EquipmentId) o;
        return automobileId == that.automobileId && equipmentId == that.equipmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(automobileId, equipmentId);
    }
}
