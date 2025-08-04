package com.college.controller.validators.contract;

import com.college.model.entity.Contract;

public class ContractWarrantyValidator implements ContractValidator {
    @Override
    public ContractValidatorResponse validate(Contract contract) {
        if (contract.getWarranty() == null) {
            return ContractValidatorResponse.INVALID_WARRANTY;
        }

        if (contract.getWarranty().getId() <= 0) {
            return ContractValidatorResponse.INVALID_WARRANTY;
        }

        return ContractValidatorResponse.VALID;
    }
}
