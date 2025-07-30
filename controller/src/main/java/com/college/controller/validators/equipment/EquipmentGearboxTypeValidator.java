package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentGearboxTypeValidator implements EquipmentValidator{
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getGearboxType() == null) {
            return EquipmentValidationResponse.INVALID_GEARBOX_TYPE;
        }

        if (equipment.getGearboxType().getId() <= 0) {
            return EquipmentValidationResponse.INVALID_GEARBOX_TYPE;
        }

        return EquipmentValidationResponse.VALID;
    }
}
