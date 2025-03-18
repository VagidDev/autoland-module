package com.college.controller;

import com.college.model.database.interfaces.ContractDAO;
import com.college.model.entity.Contract;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContractControllerTest {
    @Mock
    private ContractDAO contractDAO;
    @InjectMocks
    private ContractController contractController;

    @Test
    void getContract_shouldReturnContract() {
        Mockito.when(contractDAO.getById(Mockito.anyInt()))
                .thenReturn(new Contract());

        Contract contract = contractController.getContract(1);
        assertNotNull(contract);
    }

    @Test
    void getContract_shouldReturnNull() {
        Mockito.when(contractDAO.getById(Mockito.anyInt())).thenReturn(null);
        Contract contract = contractController.getContract(1);
        assertNull(contract);
    }

    @Test
    void getAllContracts_shouldReturnContracts() {
        Mockito.when(contractDAO.getAll())
                .thenReturn(List.of(
                        new Contract(),
                        new Contract(),
                        new Contract()
                ));

        List<Contract> contracts = contractController.getAllContracts();
        assertEquals(3, contracts.size());
    }
}