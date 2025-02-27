package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.SuspensionType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SuspensionTypeImplTest {
    private SuspensionTypeImpl suspensionTypeImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        suspensionTypeImpl = new SuspensionTypeImpl();
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

    private SuspensionType createTestSuspensionType() {
        SuspensionType suspensionType = new SuspensionType();
        suspensionType.setName("Test Suspension Type");
        return suspensionType;
    }

    @Test
    void save_shouldSaveSuspensionType() {
        SuspensionType suspensionType = createTestSuspensionType();
        suspensionTypeImpl.save(suspensionType);

        SuspensionType fetchedSuspensionType = suspensionTypeImpl.getById(suspensionType.getId());

        assertNotNull(fetchedSuspensionType);
        assertEquals(suspensionType.getName(), fetchedSuspensionType.getName());
    }

    @Test
    void getById_shouldReturnSuspensionType_whenExists() {
        SuspensionType suspensionType = createTestSuspensionType();
        suspensionTypeImpl.save(suspensionType);

        SuspensionType fetchedSuspensionType = suspensionTypeImpl.getById(suspensionType.getId());

        assertNotNull(fetchedSuspensionType);
    }

    @Test
    void getById_shouldReturnNull_whenSuspensionTypeDoesNotExist() {
        SuspensionType fetchedSuspensionType = suspensionTypeImpl.getById(-1); // Несуществующий ID
        assertNull(fetchedSuspensionType);
    }

    @Test
    void getAll_shouldReturnListOfSuspensionTypes() {
        List<SuspensionType> suspensionTypes = suspensionTypeImpl.getAll();
        assertNotNull(suspensionTypes);
    }

    @Test
    void update_shouldUpdateSuspensionType() {
        SuspensionType suspensionType = createTestSuspensionType();
        suspensionTypeImpl.save(suspensionType);

        suspensionType.setName("Updated Suspension Type");
        suspensionTypeImpl.update(suspensionType);

        SuspensionType updatedSuspensionType = suspensionTypeImpl.getById(suspensionType.getId());
        assertEquals("Updated Suspension Type", updatedSuspensionType.getName());
    }

    @Test
    void delete_shouldRemoveSuspensionType_whenExists() throws CascadeDependencyException {
        SuspensionType suspensionType = createTestSuspensionType();
        suspensionTypeImpl.save(suspensionType);

        suspensionTypeImpl.delete(suspensionType);
        SuspensionType deletedSuspensionType = suspensionTypeImpl.getById(suspensionType.getId());

        assertNull(deletedSuspensionType);
    }

    @Test
    void delete_shouldThrowException_whenSuspensionTypeHasDependencies() {
        SuspensionType suspensionType = suspensionTypeImpl.getById(1);

        // Макет: Нужно создать ситуацию, при которой удаление вызовет CascadeDependencyException
        assertThrows(CascadeDependencyException.class, () -> {
            suspensionTypeImpl.delete(suspensionType);
        });
    }

    @Test
    void deleteByID_shouldRemoveSuspensionType_whenExists() throws CascadeDependencyException {
        SuspensionType suspensionType = createTestSuspensionType();
        suspensionTypeImpl.save(suspensionType);

        suspensionTypeImpl.deleteByID(suspensionType.getId());
        SuspensionType deletedSuspensionType = suspensionTypeImpl.getById(suspensionType.getId());

        assertNull(deletedSuspensionType);
    }

    @Test
    void deleteByID_shouldThrowException_whenSuspensionTypeHasDependencies() {
        SuspensionType suspensionType = suspensionTypeImpl.getById(1);

        // Макет для ситуации, когда удаление невозможно из-за зависимостей
        assertThrows(CascadeDependencyException.class, () -> {
            suspensionTypeImpl.deleteByID(suspensionType.getId());
        });
    }
}
