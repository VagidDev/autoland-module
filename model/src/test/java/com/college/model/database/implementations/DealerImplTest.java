package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.Dealer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DealerImplTest {
    private DealerImpl dealerImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        dealerImpl = new DealerImpl();
        session = SessionManager.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        if (transaction != null) {
            transaction.rollback(); // Откатываем все изменения
        }
        session.close();
    }

    private Dealer createTestDealer() {
        Dealer dealer = new Dealer();
        dealer.setName("Test Dealer");
        dealer.setAddress("123 Test Street");
        dealer.setTelephone("+123456789");
        dealer.setFax("+987654321");
        return dealer;
    }

    @Test
    void getById_shouldReturnDealer() {
        Dealer dealer = dealerImpl.getById(1);
        assertNotNull(dealer);
    }

    @Test
    void getById_shouldReturnNull() {
        Dealer dealer = dealerImpl.getById(99999999);
        assertNull(dealer);
    }

    @Test
    void save_shouldSaveDealer() {
        Dealer dealer = createTestDealer();
        dealerImpl.save(dealer);

        Dealer fetchedDealer = dealerImpl.getById(dealer.getId());

        assertEquals(dealer.getId(), fetchedDealer.getId());
    }

    @Test
    void getAll_shouldReturnAllDealers() {
        List<Dealer> dealers = dealerImpl.getAll();
        assertNotNull(dealers);
    }

    @Test
    void update_shouldUpdateDealer() {
        Dealer dealer = createTestDealer();
        dealerImpl.save(dealer);

        dealer.setName("Updated Dealer");
        dealer.setAddress("456 Updated Street");
        dealer.setTelephone("+111111111");
        dealer.setFax("+222222222");

        dealerImpl.update(dealer);

        Dealer updatedDealer = dealerImpl.getById(dealer.getId());
        assertEquals("Updated Dealer", updatedDealer.getName());
        assertEquals("456 Updated Street", updatedDealer.getAddress());
        assertEquals("+111111111", updatedDealer.getTelephone());
        assertEquals("+222222222", updatedDealer.getFax());
    }

    @Test
    void delete_shouldDeleteDealer() throws CascadeDependencyException {
        Dealer dealer = createTestDealer();
        dealerImpl.save(dealer);

        dealerImpl.delete(dealer);
        Dealer deletedDealer = dealerImpl.getById(dealer.getId());

        assertNull(deletedDealer);
    }

    @Test
    void delete_shouldThrowCascadeDependencyException() {
        Dealer dealer = dealerImpl.getById(1);
        assertThrows(CascadeDependencyException.class, () -> dealerImpl.delete(dealer));
    }

}
