package com.college.controller.validators.contract;

import com.college.model.entity.Contract;

public class ContractDealerValidator implements ContractValidator {
    @Override
    public ContractValidatorResponse validate(Contract contract) {
        if (contract.getDealer() == null)
            return ContractValidatorResponse.INVALID_DEALER;

        if (contract.getDealer().getId() <= 0)
            return ContractValidatorResponse.INVALID_DEALER;

        return ContractValidatorResponse.VALID;
    }
}
