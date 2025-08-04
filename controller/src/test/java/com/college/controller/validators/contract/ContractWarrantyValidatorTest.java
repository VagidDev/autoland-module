package com.college.controller.validators.contract;

import com.college.model.entity.Contract;
import com.college.model.entity.User;
import com.college.model.entity.Warranty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractWarrantyValidatorTest {
    private final Contract contract = new Contract();
    private final ContractWarrantyValidator validator = new ContractWarrantyValidator();

    @Test
    void validate_shouldBeValid() {
        Warranty warranty = new Warranty();
        warranty.setId(1);
        contract.setWarranty(warranty);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        contract.setWarranty(null);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_WARRANTY,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        contract.setWarranty(new Warranty());
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_WARRANTY,  response);
    }
}