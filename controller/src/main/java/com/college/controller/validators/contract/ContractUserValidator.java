package com.college.controller.validators.contract;

import com.college.model.entity.Contract;

public class ContractUserValidator implements ContractValidator{
    @Override
    public ContractValidatorResponse validate(Contract contract) {
        if (contract.getUser() == null) {
            return ContractValidatorResponse.INVALID_USER;
        }

        if (contract.getUser().getId() <= 0) {
            return ContractValidatorResponse.INVALID_USER;
        }

        return ContractValidatorResponse.VALID;
    }
}
