package com.college.controller.validators.equipment;

import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.Equipment;

public class EquipmentBodyKitValidator extends EmptyStringValidator<Equipment, EquipmentValidationResponse> implements EquipmentValidator {
    @Override
    protected String getStringToValidate(Equipment equipment) {
        return equipment.getBodyKit();
    }

    @Override
    protected EquipmentValidationResponse getValidResponse() {
        return EquipmentValidationResponse.VALID;
    }

    @Override
    protected EquipmentValidationResponse getInvalidResponse() {
        return EquipmentValidationResponse.INVALID_BODY_KIT;
    }
}
