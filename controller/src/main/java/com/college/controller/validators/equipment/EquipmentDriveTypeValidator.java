package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentDriveTypeValidator implements EquipmentValidator {
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getDriveType() == null) {
            return EquipmentValidationResponse.INVALID_DRIVE_TYPE;
        }

        if (equipment.getDriveType().getId() <= 0) {
            return EquipmentValidationResponse.INVALID_DRIVE_TYPE;
        }

        return EquipmentValidationResponse.VALID;
    }
}
