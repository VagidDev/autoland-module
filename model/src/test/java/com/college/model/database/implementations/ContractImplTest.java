package com.college.model.database.implementations;

import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.*;
import com.college.model.entity.keys.EquipmentId;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContractImplTest {

    private static ContractImpl contractImpl = new ContractImpl();
    private static UserImpl userImpl = new UserImpl();
    private static DealerImpl dealerImpl = new DealerImpl();
    private static AutomobileImpl automobileImpl = new AutomobileImpl();
    private static EquipmentImpl equipmentImpl = new EquipmentImpl();
    private static WarrantyImpl warrantyImpl = new WarrantyImpl();

    private static Contract savedContract;

    public Contract createContract() {
        User user = userImpl.getById(1);
        Dealer dealer = dealerImpl.getById(1);
        Automobile automobile = automobileImpl.getById(1);
        Equipment equipment = equipmentImpl.getById(new EquipmentId(1, 1));
        Warranty warranty = warrantyImpl.getById(1);
        Date date = new Date();

        return new Contract(0, user, dealer, equipment, warranty, date);
    }

    @Test
    @Order(1)
    void save_shouldSaveContract() {
        Contract contract = createContract();
        savedContract = contractImpl.save(contract);

        assertNotNull(savedContract);
    }

    @Test
    @Order(2)
    void getById_shouldReturnContract() {
        Contract contract = contractImpl.getById(savedContract.getId());

        assertNotNull(contract);
        assertEquals(savedContract.getId(), contract.getId());
    }

    @Test
    @Order(3)
    void getAll_shouldReturnAllContracts() {
        List<Contract> contracts = contractImpl.getAll();

        assertFalse(contracts.isEmpty());
    }

    @Test
    @Order(4)
    void update_shouldUpdateContract() {
        savedContract.setConclusionDate(new Date());
        contractImpl.update(savedContract);
        Contract updatedContract = contractImpl.getById(savedContract.getId());

        assertNotNull(updatedContract);
        assertEquals(savedContract.getConclusionDate().getTime() / 10000,
                updatedContract.getConclusionDate().getTime() / 10000); //rounding datetime
    }

    @Test
    @Order(5)
    void delete_shouldDeleteContract() throws CascadeDependencyException {
        contractImpl.delete(savedContract);
        Contract deletedContract = contractImpl.getById(savedContract.getId());

        assertNull(deletedContract);
    }
}
