package com.college.controller.validators.contract;

import com.college.model.entity.Contract;

public class ContractEquipmentValidator implements ContractValidator {
    @Override
    public ContractValidatorResponse validate(Contract contract) {
        if (contract.getEquipment() == null) {
            return ContractValidatorResponse.INVALID_EQUIPMENT;
        }

        if (contract.getEquipment().getId() == null) {
            return ContractValidatorResponse.INVALID_EQUIPMENT;
        }

        return ContractValidatorResponse.VALID;
    }
}
