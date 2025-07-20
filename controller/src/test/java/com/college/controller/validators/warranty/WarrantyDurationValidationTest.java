package com.college.controller.validators.warranty;

import com.college.model.entity.Warranty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarrantyDurationValidationTest {
    private final Warranty warranty = new Warranty();
    private final WarrantyDurationValidation warrantyDurationValidation = new WarrantyDurationValidation();

    @Test
    void validate_shouldBeValid(){
        warranty.setDuration(60);
        WarrantyValidationResponse response = warrantyDurationValidation.validate(warranty);
        assertEquals(WarrantyValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_NegativeDuration(){
        warranty.setDuration(-25);
        WarrantyValidationResponse response = warrantyDurationValidation.validate(warranty);
        assertEquals(WarrantyValidationResponse.INVALID_DURATION, response);
    }

    @Test
    void validate_shouldNotBeValid_ZeroDuration(){
        warranty.setDuration(0);
        WarrantyValidationResponse response = warrantyDurationValidation.validate(warranty);
        assertEquals(WarrantyValidationResponse.INVALID_DURATION, response);
    }
}