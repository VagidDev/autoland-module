package com.college.controller.validators.dealer;

import com.college.model.entity.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTelephoneValidatorTest {
    private final DealerTelephoneValidator dealerTelephoneValidator = new DealerTelephoneValidator();
    private final Dealer dealer = new Dealer();

    @Test
    void validate_shouldBeValid() {
        dealer.setTelephone("+37367258196");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        dealer.setTelephone(null);
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_Empty() {
        dealer.setTelephone("     ");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_TooShort() {
        dealer.setTelephone("+373292196");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_TooLong() {
        dealer.setTelephone("+3732921966554932");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_ExistsLetter() {
        dealer.setTelephone("+37367292i96");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_StartNotFromPlus() {
        dealer.setTelephone("37367292196");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_IncorrectCountryCode() {
        dealer.setTelephone("+77767292196");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_ForeignSymbols() {
        dealer.setTelephone("+37387%5%%^*");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_DoublePlus() {
        dealer.setTelephone("++3732584963");
        DealerValidationResponse response = dealerTelephoneValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_PHONE, response);
    }
}