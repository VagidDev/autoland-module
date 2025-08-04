package com.college.controller.validators.contract;

import com.college.model.entity.Contract;
import com.college.model.entity.Dealer;
import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractDealerValidatorTest {
    private final Contract contract = new Contract();
    private final ContractDealerValidator validator = new ContractDealerValidator();

    @Test
    void validate_shouldBeValid() {
        Dealer dealer = new Dealer();
        dealer.setId(1);
        contract.setDealer(dealer);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        contract.setDealer(null);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_DEALER,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        contract.setDealer(new Dealer());
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_DEALER,  response);
    }
}