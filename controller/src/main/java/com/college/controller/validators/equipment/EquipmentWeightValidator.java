package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentWeightValidator implements EquipmentValidator{
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getWeight() <= 0) {
            return EquipmentValidationResponse.INVALID_WEIGHT;
        }
        return EquipmentValidationResponse.VALID;
    }
}
