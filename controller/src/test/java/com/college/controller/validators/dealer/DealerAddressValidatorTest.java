package com.college.controller.validators.dealer;

import com.college.model.entity.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerAddressValidatorTest {
    private final DealerAddressValidator dealerAddressValidator = new DealerAddressValidator();
    private final Dealer dealer = new Dealer();

    @Test
    void validate_shouldBeValid() {
        dealer.setAddress("Puskin 3/2");
        DealerValidationResponse response = dealerAddressValidator.validate(dealer);
        assertEquals(DealerValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        dealer.setAddress(null);
        DealerValidationResponse response = dealerAddressValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_ADDRESS, response);
    }

    @Test
    void validate_shouldNotBeValid_Empty() {
        dealer.setAddress("                ");
        DealerValidationResponse response = dealerAddressValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_ADDRESS, response);
    }
}