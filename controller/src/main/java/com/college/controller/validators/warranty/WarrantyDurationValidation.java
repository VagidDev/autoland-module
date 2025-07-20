package com.college.controller.validators.warranty;

import com.college.model.entity.Warranty;

public class WarrantyDurationValidation implements WarrantyValidator {
    @Override
    public WarrantyValidationResponse validate(Warranty warranty) {
        if (warranty.getDuration() <= 0) {
            return WarrantyValidationResponse.INVALID_DURATION;
        }
        return WarrantyValidationResponse.VALID;
    }
}
