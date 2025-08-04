package com.college.controller.validators.contract;

import com.college.controller.validators.equipment.EquipmentValidationResponse;
import com.college.model.entity.Automobile;
import com.college.model.entity.Contract;
import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractUserValidatorTest {
    private final Contract contract = new Contract();
    private final ContractUserValidator validator = new ContractUserValidator();

    @Test
    void validate_shouldBeValid() {
        User user = new User();
        user.setId(1);
        contract.setUser(user);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        contract.setUser(null);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_USER,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        contract.setUser(new User());
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_USER,  response);
    }
}