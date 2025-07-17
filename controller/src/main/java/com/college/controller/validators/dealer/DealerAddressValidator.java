package com.college.controller.validators.dealer;

import com.college.controller.validators.EmptyStringValidator;
import com.college.model.entity.Dealer;

public class DealerAddressValidator extends EmptyStringValidator<Dealer, DealerValidationResponse> implements DealerValidator {
    @Override
    protected String getStringToValidate(Dealer dealer) {
        return dealer.getAddress();
    }

    @Override
    protected DealerValidationResponse getValidResponse() {
        return DealerValidationResponse.VALID;
    }

    @Override
    protected DealerValidationResponse getInvalidResponse() {
        return DealerValidationResponse.INVALID_ADDRESS;
    }
}
