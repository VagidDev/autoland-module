package com.college.controller.validators.automobile;

import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.Automobile;

public class AutomobileMarkValidator extends EmptyStringValidator<Automobile, AutomobileValidationResponse> implements AutomobileValidator {
    @Override
    protected String getStringToValidate(Automobile automobile) {
        return automobile.getMark();
    }

    @Override
    protected AutomobileValidationResponse getValidResponse() {
        return AutomobileValidationResponse.VALID;
    }

    @Override
    protected AutomobileValidationResponse getInvalidResponse() {
        return AutomobileValidationResponse.INVALID_MARK;
    }
}
