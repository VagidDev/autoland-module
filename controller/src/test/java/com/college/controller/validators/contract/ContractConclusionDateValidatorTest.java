package com.college.controller.validators.contract;

import com.college.model.entity.Contract;
import com.college.model.entity.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ContractConclusionDateValidatorTest {
    private final Contract contract = new Contract();
    private final ContractConclusionDateValidator validator = new ContractConclusionDateValidator();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    void validate_shouldBeValid() {
        contract.setConclusionDate(new Date());
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        contract.setConclusionDate(null);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_CONCLUSION_DATE,  response);
    }

    @Test
    void validate_shouldNotBeValid_TooOld() throws ParseException {
        contract.setConclusionDate(sdf.parse("1856-10-03"));
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_CONCLUSION_DATE,  response);
    }

    @Test
    void validate_shouldNotBeValid_DoesNotExistsYet() throws ParseException {
        contract.setConclusionDate(sdf.parse("2029-10-03"));
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_CONCLUSION_DATE,  response);
    }
}