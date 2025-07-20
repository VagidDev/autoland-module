package com.college.controller.validators.automobile;

import com.college.model.entity.Automobile;

public class AutomobilePlaceCountValidator implements AutomobileValidator{
    @Override
    public AutomobileValidationResponse validate(Automobile automobile) {
        if (automobile.getPlaceCount() <= 0) {
            return AutomobileValidationResponse.INVALID_PLACE_COUNT;
        }
        return AutomobileValidationResponse.VALID;
    }
}
