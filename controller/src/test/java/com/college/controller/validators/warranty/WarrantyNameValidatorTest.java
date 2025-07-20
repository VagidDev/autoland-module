package com.college.controller.validators.warranty;

import com.college.model.entity.Warranty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarrantyNameValidatorTest {
    private final Warranty warranty = new Warranty();
    private final WarrantyNameValidator warrantyNameValidator = new WarrantyNameValidator();

    @Test
    void validate_shouldBeValid() {
        warranty.setName("Exclusive warranty");
        WarrantyValidationResponse response = warrantyNameValidator.validate(warranty);
        assertEquals(WarrantyValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        warranty.setName(null);
        WarrantyValidationResponse response = warrantyNameValidator.validate(warranty);
        assertEquals(WarrantyValidationResponse.INVALID_NAME, response);
    }

    @Test
    void validate_shouldNotBeValid_Empty() {
        warranty.setName("                    ");
        WarrantyValidationResponse response = warrantyNameValidator.validate(warranty);
        assertEquals(WarrantyValidationResponse.INVALID_NAME, response);
    }
}