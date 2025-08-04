package com.college.controller.validators.contract;

import com.college.model.entity.Contract;
import com.college.model.entity.Equipment;
import com.college.model.entity.User;
import com.college.model.entity.keys.EquipmentId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractEquipmentValidatorTest {
    private final Contract contract = new Contract();
    private final ContractEquipmentValidator validator = new ContractEquipmentValidator();

    @Test
    void validate_shouldBeValid() {
        Equipment equipment = new Equipment();
        equipment.setId(new EquipmentId(5, 8));
        contract.setEquipment(equipment);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.VALID,  response);
    }

    @Test
    void validate_shouldNotBeValid_Null() {
        contract.setEquipment(null);
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_EQUIPMENT,  response);
    }

    @Test
    void validate_shouldNotBeValid_New() {
        contract.setEquipment(new  Equipment());
        ContractValidatorResponse response = validator.validate(contract);
        assertEquals(ContractValidatorResponse.INVALID_EQUIPMENT,  response);
    }
}