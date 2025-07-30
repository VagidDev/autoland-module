package com.college.controller.validators.equipment;

import com.college.model.entity.Equipment;

public class EquipmentEngineVolumeValidator implements EquipmentValidator {
    @Override
    public EquipmentValidationResponse validate(Equipment equipment) {
        if (equipment.getEngineVolume() < 0 || equipment.getEngineVolume() > 50) {
            return EquipmentValidationResponse.INVALID_ENGINE_VOLUME;
        }

        return EquipmentValidationResponse.VALID;
    }
}
