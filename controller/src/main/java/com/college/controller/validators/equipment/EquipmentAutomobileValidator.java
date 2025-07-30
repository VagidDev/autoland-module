package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentAutomobileValidator implements EquipmentValidator {
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getAutomobile() == null) {
            return EquipmentValidationResponse.INVALID_AUTOMOBILE;
        }

        if (equipment.getAutomobile().getId() <= 0) {
            return EquipmentValidationResponse.INVALID_AUTOMOBILE;
        }

        return EquipmentValidationResponse.VALID;
    }
}
