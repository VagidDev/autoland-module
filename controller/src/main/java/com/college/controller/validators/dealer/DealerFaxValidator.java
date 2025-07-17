package com.college.controller.validators.dealer;

import com.college.controller.validators.PhoneNumberValidator;
import com.college.model.entity.Dealer;

public class DealerFaxValidator extends PhoneNumberValidator<Dealer, DealerValidationResponse> implements DealerValidator {
    @Override
    protected String getStringToValidate(Dealer dealer) {
        return dealer.getFax();
    }

    @Override
    protected DealerValidationResponse getValidResponse() {
        return DealerValidationResponse.VALID;
    }

    @Override
    protected DealerValidationResponse getInvalidResponse() {
        return DealerValidationResponse.INVALID_FAX_PHONE;
    }
}
