package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentEngineTypeValidator implements EquipmentValidator{
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getEngineType() == null) {
            return EquipmentValidationResponse.INVALID_ENGINE_TYPE;
        }

        if (equipment.getEngineType().getId() <= 0) {
            return EquipmentValidationResponse.INVALID_ENGINE_TYPE;
        }

        return EquipmentValidationResponse.VALID;
    }
}
