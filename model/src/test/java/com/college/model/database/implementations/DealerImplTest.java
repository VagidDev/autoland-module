package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.Dealer;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealerImplTest {
    private final DealerImpl dealerRepository = new DealerImpl();

    @Test
    void getById_shouldReturnDealer() {
        Dealer dealer = dealerRepository.getById(1);
        assertEquals(1, dealer.getId());
    }

    @Test
    void getById_shouldNotTrowExceptionReturnNull() {
        assertDoesNotThrow(() -> dealerRepository.getById(99999));
    }

    @Test
    void getAll_shouldReturnAllDealers() {
        List<Dealer> dealers = dealerRepository.getAll();
        assertNotEquals(0, dealers.size());
    }

    @Test
    void save_shouldSaveDealer() {
        Dealer dealer = new Dealer();
        dealer.setName("Test Dealer");
        dealer.setAddress("Some address");
        dealer.setTelephone("+37367292196");
        dealer.setFax("+37367292196");

        Dealer savedDealer = dealerRepository.save(dealer);

        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(savedDealer);
            session.getTransaction().commit();
        }

        assertNotNull(savedDealer);

    }

    @Test
    void update_shouldUpdateDealer() {
        Dealer originalDealer = dealerRepository.getById(1);
        Dealer updatedDealer = dealerRepository.getById(1);
        updatedDealer.setName("Updated Dealer");

        dealerRepository.update(updatedDealer);

        updatedDealer = null;

        updatedDealer = dealerRepository.getById(1);

        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(originalDealer);
            session.getTransaction().commit();
        }

        assertEquals("Updated Dealer", updatedDealer.getName());
    }

    @Test
    void delete_shouldDeleteDealer() throws CascadeDependencyException {
        Dealer dealer = new Dealer();
        dealer.setName("Test Dealer");
        dealer.setAddress("Some address");
        dealer.setTelephone("+37367292196");
        dealer.setFax("+37367292196");
        int initialSize = dealerRepository.getAll().size();
        dealerRepository.save(dealer);

        dealerRepository.delete(dealer);
        int resultSize = dealerRepository.getAll().size();

        assertEquals(initialSize, resultSize);
    }

    @Test
    void delete_shouldThrowCascadeDependencyException() {
        Dealer dealer = dealerRepository.getById(1);
        assertThrows(CascadeDependencyException.class, () -> dealerRepository.delete(dealer));
    }
}