package com.college.controller.validators.dealer;

import com.college.model.entity.Dealer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerFaxValidatorTest {
    private final DealerFaxValidator dealerFaxValidator = new DealerFaxValidator();
    private final Dealer dealer = new Dealer();

    @Test
    void validate_shouldBeValid() {
        dealer.setFax("+37367258196");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.VALID, response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        dealer.setFax(null);
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_Empty() {
        dealer.setFax("     ");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_TooShort() {
        dealer.setFax("+373292196");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_TooLong() {
        dealer.setFax("+3732921966554932");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_ExistsLetter() {
        dealer.setFax("+37367292i96");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_StartNotFromPlus() {
        dealer.setFax("37367292196");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_IncorrectCountryCode() {
        dealer.setFax("+77767292196");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_ForeignSymbols() {
        dealer.setFax("+37387%5%%^*");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }

    @Test
    void validate_shouldNotBeValid_DoublePlus() {
        dealer.setFax("++3732584963");
        DealerValidationResponse response = dealerFaxValidator.validate(dealer);
        assertEquals(DealerValidationResponse.INVALID_FAX_PHONE, response);
    }
}