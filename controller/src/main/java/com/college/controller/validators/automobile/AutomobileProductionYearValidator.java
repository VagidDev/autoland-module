package com.college.controller.validators.automobile;

import com.college.model.entity.Automobile;

import java.time.LocalDate;
import java.util.Date;

public class AutomobileProductionYearValidator implements AutomobileValidator {

    @Override
    public AutomobileValidationResponse validate(Automobile automobile) {
        if (automobile.getProdYear() < 1888
                || automobile.getProdYear() > LocalDate.now().getYear()) {
            return AutomobileValidationResponse.INVALID_PRODUCTION_YEAR;
        }
        return AutomobileValidationResponse.VALID;
    }
}
