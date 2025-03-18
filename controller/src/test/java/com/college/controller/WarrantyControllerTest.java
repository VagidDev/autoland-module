package com.college.controller;

import com.college.model.database.interfaces.WarrantyDAO;
import com.college.model.entity.Dealer;
import com.college.model.entity.Warranty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WarrantyControllerTest {
    @Mock
    private WarrantyDAO warrantyDAO;
    @InjectMocks
    private WarrantyController warrantyController;

    @Test
    void getWarranty_shouldReturnWarranty() {
        Mockito.when(warrantyDAO.getById(Mockito.anyInt()))
                .thenReturn(new Warranty(1, "Test 1", 24, 2500));
        Warranty warranty = warrantyController.getWarranty(1);
        assertEquals(1, warranty.getId());
    }

    @Test
    void getAllWarranties_shouldReturnWarranties() {
        Mockito.when(warrantyDAO.getAll())
                .thenReturn(List.of(
                        new Warranty(1, "Test 1", 24, 2500),
                        new Warranty(2, "Test 2", 24, 4000),
                        new Warranty(1, "Test 3", 24, 5000)
                        ));

        List<Warranty> warranties = warrantyController.getAllWarranty();
        assertEquals(3, warranties.size());
    }
}