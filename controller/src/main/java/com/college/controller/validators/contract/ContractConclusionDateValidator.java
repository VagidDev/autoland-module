package com.college.controller.validators.contract;

import com.college.controller.validators.DateFormatValidator;
import com.college.model.entity.Contract;

import java.util.Date;

public class ContractConclusionDateValidator extends DateFormatValidator<Contract, ContractValidatorResponse> implements ContractValidator {

    @Override
    public ContractValidatorResponse validate(Contract contract) {
        ContractValidatorResponse response = super.validate(contract);

        if (response != getValidResponse()) {
            return response;
        }

        if (new Date().before(contract.getConclusionDate())) {
            return getInvalidResponse();
        }

        return getValidResponse();
    }

    @Override
    protected Date getDate(Contract contract) {
        return contract.getConclusionDate();
    }

    @Override
    protected ContractValidatorResponse getValidResponse() {
        return ContractValidatorResponse.VALID;
    }

    @Override
    protected ContractValidatorResponse getInvalidResponse() {
        return ContractValidatorResponse.INVALID_CONCLUSION_DATE;
    }
}
