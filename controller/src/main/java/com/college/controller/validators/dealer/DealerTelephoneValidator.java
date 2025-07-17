package com.college.controller.validators.dealer;

import com.college.controller.validators.PhoneNumberValidator;
import com.college.model.entity.Dealer;

public class DealerTelephoneValidator extends PhoneNumberValidator<Dealer, DealerValidationResponse> implements DealerValidator {
    @Override
    protected String getStringToValidate(Dealer dealer) {
        return dealer.getTelephone();
    }

    @Override
    protected DealerValidationResponse getValidResponse() {
        return DealerValidationResponse.VALID;
    }

    @Override
    protected DealerValidationResponse getInvalidResponse() {
        return DealerValidationResponse.INVALID_PHONE;
    }
}
