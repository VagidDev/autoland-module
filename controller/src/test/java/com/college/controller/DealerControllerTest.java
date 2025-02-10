package com.college.controller;

import com.college.model.Dealer;
import com.college.model.database.interfaces.DealerDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DealerControllerTest {
    @Mock
    private DealerDAO dealerDAO;
    @InjectMocks
    private DealerController dealerController;

    @Test
    void getDealerById_shouldReturnDealer() {
        Mockito.when(dealerDAO.getById(Mockito.anyInt()))
                .thenReturn(new Dealer(1, "Dealer", "Some Address", "+37367292196", "02224239329"));
        Dealer dealer = dealerController.getDealerById(1);
        assertEquals(1, dealer.getId());
    }

    @Test
    void getAllDealers_shouldReturnDealers() {
        Mockito.when(dealerDAO.getAll())
                .thenReturn(List.of(
                        new Dealer(1, "Dealer", "Some Address", "+37367292196", "02224239329"),
                        new Dealer(2, "Another dealer", "Another address", "+37367292196", "02224239329"),
                        new Dealer(2, "Also dealer", "Also address", "+37367292196", "02224239329")));
        List<Dealer> dealers = dealerController.getAllDealers();
        assertEquals(3, dealers.size());
    }

}