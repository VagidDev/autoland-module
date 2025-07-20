package com.college.controller.validators.automobile;

import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.Automobile;

public class AutomobileBodyTypeValidator implements AutomobileValidator {

    @Override
    public AutomobileValidationResponse validate(Automobile automobile) {
        if (automobile.getBodyType() == null) {
            return AutomobileValidationResponse.INVALID_BODY_TYPE;
        }

        if (automobile.getBodyType().getId() == 0) {
            return AutomobileValidationResponse.INVALID_BODY_TYPE;
        }
        return AutomobileValidationResponse.VALID;
    }
}
