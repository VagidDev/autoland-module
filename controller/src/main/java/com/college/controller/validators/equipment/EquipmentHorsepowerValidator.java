package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentHorsepowerValidator implements EquipmentValidator {
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getHorsepower() <= 0) {
            return EquipmentValidationResponse.INVALID_HORSEPOWER;
        }

        return EquipmentValidationResponse.VALID;
    }
}
