package com.college.controller.validators.warranty;

import com.college.model.entity.Warranty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarrantyPriceValidatorTest {
    private final Warranty warranty = new Warranty();
    private final WarrantyPriceValidator warrantyPriceValidator = new WarrantyPriceValidator();

    @Test
    void validate_shouldBeValid() {
        warranty.setPrice(12500);
        WarrantyValidationResponse response = warrantyPriceValidator.validate(warranty);
        assertEquals(WarrantyValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_NegativePrice() {
        warranty.setPrice(-12500);
        WarrantyValidationResponse response = warrantyPriceValidator.validate(warranty);
        assertEquals(WarrantyValidationResponse.INVALID_PRICE, response);
    }

    @Test
    void validate_shouldNotBeValid_ZeroPrice() {
        warranty.setPrice(0);
        WarrantyValidationResponse response = warrantyPriceValidator.validate(warranty);
        assertEquals(WarrantyValidationResponse.INVALID_PRICE, response);
    }
}