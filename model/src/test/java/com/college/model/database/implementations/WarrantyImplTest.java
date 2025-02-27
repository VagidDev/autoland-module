package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.Warranty;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WarrantyImplTest {
    private WarrantyImpl warrantyImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        warrantyImpl = new WarrantyImpl();
        session = SessionManager.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        if (transaction != null) {
            transaction.rollback(); // Откат изменений после теста
        }
        session.close();
    }

    private Warranty createTestWarranty() {
        return new Warranty(0, "Basic Warranty", 12, 199.99);
    }

    @Test
    void save_shouldSaveWarranty() {
        Warranty warranty = createTestWarranty();
        warrantyImpl.save(warranty);

        Warranty fetchedWarranty = warrantyImpl.getById(warranty.getId());

        assertNotNull(fetchedWarranty);
        assertEquals(warranty.getName(), fetchedWarranty.getName());
        assertEquals(warranty.getDuration(), fetchedWarranty.getDuration());
        assertEquals(warranty.getPrice(), fetchedWarranty.getPrice());
    }

    @Test
    void getById_shouldReturnWarranty_whenExists() {
        Warranty warranty = createTestWarranty();
        warrantyImpl.save(warranty);

        Warranty fetchedWarranty = warrantyImpl.getById(warranty.getId());

        assertNotNull(fetchedWarranty);
        assertEquals(warranty.getName(), fetchedWarranty.getName());
    }

    @Test
    void getById_shouldReturnNull_whenWarrantyDoesNotExist() {
        Warranty fetchedWarranty = warrantyImpl.getById(-1); // Несуществующий ID
        assertNull(fetchedWarranty);
    }

    @Test
    void getAll_shouldReturnListOfWarranties() {
        List<Warranty> warranties = warrantyImpl.getAll();
        assertNotNull(warranties);
    }

    @Test
    void update_shouldUpdateWarranty() {
        Warranty warranty = createTestWarranty();
        warrantyImpl.save(warranty);

        warranty.setName("Extended Warranty");
        warranty.setDuration(24);
        warranty.setPrice(299.99);
        warrantyImpl.update(warranty);

        Warranty updatedWarranty = warrantyImpl.getById(warranty.getId());

        assertEquals("Extended Warranty", updatedWarranty.getName());
        assertEquals(24, updatedWarranty.getDuration());
        assertEquals(299.99, updatedWarranty.getPrice());
    }

    @Test
    void delete_shouldRemoveWarranty_whenExists() throws CascadeDependencyException {
        Warranty warranty = createTestWarranty();
        warrantyImpl.save(warranty);

        warrantyImpl.delete(warranty);
        Warranty deletedWarranty = warrantyImpl.getById(warranty.getId());

        assertNull(deletedWarranty);
    }

    @Test
    void deleteByID_shouldRemoveWarranty_whenExists() throws CascadeDependencyException {
        Warranty warranty = createTestWarranty();
        warrantyImpl.save(warranty);

        warrantyImpl.deleteByID(warranty.getId());
        Warranty deletedWarranty = warrantyImpl.getById(warranty.getId());

        assertNull(deletedWarranty);
    }

    @Test
    void delete_shouldNotRemoveWarranty_throwCascadeDependencyException()  {
        Warranty warranty = warrantyImpl.getById(1);

        assertThrows(CascadeDependencyException.class, () -> warrantyImpl.delete(warranty));
    }

    @Test
    void deleteByID_shouldNotRemoveWarranty_throwCascadeDependencyException() {
        assertThrows(CascadeDependencyException.class, () -> warrantyImpl.deleteByID(1));
    }
}
