package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentSpeedCountValidator implements EquipmentValidator {
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getSpeedCount() <= 0 || equipment.getSpeedCount() > 50) {
            return EquipmentValidationResponse.INVALID_SPEED_COUNT;
        }

        return EquipmentValidationResponse.VALID;
    }
}
