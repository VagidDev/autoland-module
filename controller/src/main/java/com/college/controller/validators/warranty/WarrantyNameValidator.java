package com.college.controller.validators.warranty;

import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.Warranty;

public class WarrantyNameValidator extends EmptyStringValidator<Warranty, WarrantyValidationResponse> implements WarrantyValidator {
    @Override
    protected String getStringToValidate(Warranty warranty) {
        return warranty.getName();
    }

    @Override
    protected WarrantyValidationResponse getValidResponse() {
        return WarrantyValidationResponse.VALID;
    }

    @Override
    protected WarrantyValidationResponse getInvalidResponse() {
        return WarrantyValidationResponse.INVALID_NAME;
    }
}
