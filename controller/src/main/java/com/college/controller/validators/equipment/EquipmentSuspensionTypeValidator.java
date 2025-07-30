package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentSuspensionTypeValidator implements EquipmentValidator{
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getSuspensionType() == null) {
            return EquipmentValidationResponse.INVALID_SUSPENSION_TYPE;
        }

        if (equipment.getSuspensionType().getId() <= 0) {
            return EquipmentValidationResponse.INVALID_SUSPENSION_TYPE;
        }

        return EquipmentValidationResponse.VALID;
    }
}
