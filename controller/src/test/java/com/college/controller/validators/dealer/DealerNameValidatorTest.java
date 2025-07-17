package com.college.controller.validators.dealer;

import com.college.model.entity.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerNameValidatorTest {
    private final DealerNameValidator dealerNameValidator = new DealerNameValidator();
    private final Dealer dealer = new Dealer();

    @Test
    void validate_shouldBeValid() {
        dealer.setName("AutoMall");
        DealerValidationResponse response = dealerNameValidator.validate(dealer);
        assertEquals(DealerValidationResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        dealer.setName(null);
        DealerValidationResponse response = dealerNameValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_NAME,  response);
    }

    @Test
    void validate_shouldNotBeValid_Empty() {
        dealer.setName("                   ");
        DealerValidationResponse response = dealerNameValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_NAME,  response);
    }
}