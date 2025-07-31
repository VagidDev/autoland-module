package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentPriceValidator implements EquipmentValidator {
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getPrice() <= 0 || equipment.getPrice() > 9999999.99) {
            return EquipmentValidationResponse.INVALID_PRICE;
        }
        return EquipmentValidationResponse.VALID;
    }
}
