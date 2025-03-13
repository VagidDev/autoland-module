package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.Automobile;
import com.college.model.entity.BodyType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AutomobileImplTest {
    private AutomobileImpl automobileImpl;
    private BodyTypeImpl bodyTypeImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        automobileImpl = new AutomobileImpl();
        bodyTypeImpl = new BodyTypeImpl();
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

    private BodyType createTestBodyType() {
        return bodyTypeImpl.getById(1);
    }

    private Automobile createTestAutomobile() {
        BodyType bodyType = createTestBodyType();
        return new Automobile(0, "Toyota", "Camry", bodyType, 5, 2022);
    }

    @Test
    void save_shouldSaveAutomobile() {
        Automobile automobile = createTestAutomobile();
        automobileImpl.save(automobile);

        Automobile fetched = automobileImpl.getById(automobile.getId());

        assertNotNull(fetched);
        assertEquals(automobile.getMark(), fetched.getMark());
        assertEquals(automobile.getModel(), fetched.getModel());
        assertEquals(automobile.getBodyType().getId(), fetched.getBodyType().getId());
    }

    @Test
    void getById_shouldReturnAutomobile_whenExists() {
        Automobile automobile = createTestAutomobile();
        automobileImpl.save(automobile);

        Automobile fetched = automobileImpl.getById(automobile.getId());

        assertNotNull(fetched);
        assertEquals(automobile.getMark(), fetched.getMark());
    }

    @Test
    void getById_shouldReturnAutomobile_whenExists_checkBodyType() {
        Automobile automobile = createTestAutomobile();
        automobileImpl.save(automobile);

        Automobile fetched = automobileImpl.getById(automobile.getId());

        assertNotNull(fetched);
        assertEquals(automobile.getMark(), fetched.getMark());
        assertEquals(automobile.getBodyType().getId(), fetched.getBodyType().getId());
    }

    @Test
    void getById_shouldReturnNull_whenAutomobileDoesNotExist() {
        Automobile fetched = automobileImpl.getById(-1);
        assertNull(fetched);
    }

    @Test
    void getAll_shouldReturnListOfAutomobiles() {
        List<Automobile> automobiles = automobileImpl.getAll();
        assertNotNull(automobiles);
    }

    @Test
    void update_shouldUpdateAutomobile() {
        Automobile automobile = createTestAutomobile();
        automobileImpl.save(automobile);

        automobile.setMark("Honda");
        automobile.setModel("Civic");
        automobile.setProdYear(2023);
        automobileImpl.update(automobile);

        Automobile updated = automobileImpl.getById(automobile.getId());

        assertEquals("Honda", updated.getMark());
        assertEquals("Civic", updated.getModel());
        assertEquals(2023, updated.getProdYear());
    }

    @Test
    void delete_shouldRemoveAutomobile_whenExists() throws CascadeDependencyException {
        Automobile automobile = createTestAutomobile();
        automobileImpl.save(automobile);

        automobileImpl.delete(automobile);
        Automobile deleted = automobileImpl.getById(automobile.getId());

        assertNull(deleted);
    }

    @Test
    void deleteByID_shouldRemoveAutomobile_whenExists() throws CascadeDependencyException {
        Automobile automobile = createTestAutomobile();
        automobileImpl.save(automobile);

        automobileImpl.deleteByID(automobile.getId());
        Automobile deleted = automobileImpl.getById(automobile.getId());

        assertNull(deleted);
    }

    @Test
    void delete_shouldThrowCascadeDependencyException_whenAutomobileHasDependencies() {
        Automobile automobile = automobileImpl.getById(1);

        assertThrows(CascadeDependencyException.class, () -> {
            automobileImpl.delete(automobile);
        });
    }

}
