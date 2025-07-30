package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentFuelTypeValidator implements EquipmentValidator {
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getFuelType() == null) {
            return EquipmentValidationResponse.INVALID_FUEL_TYPE;
        }

        if (equipment.getFuelType().getId() <= 0) {
            return EquipmentValidationResponse.INVALID_FUEL_TYPE;
        }

        return EquipmentValidationResponse.VALID;
    }
}
