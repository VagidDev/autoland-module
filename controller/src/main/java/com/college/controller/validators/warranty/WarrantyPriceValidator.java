package com.college.controller.validators.warranty;

import com.college.model.entity.Warranty;

public class WarrantyPriceValidator implements WarrantyValidator {
    @Override
    public WarrantyValidationResponse validate(Warranty warranty) {
        if (warranty.getPrice() <= 0) {
            return WarrantyValidationResponse.INVALID_PRICE;
        }
        return WarrantyValidationResponse.VALID;
    }
}
