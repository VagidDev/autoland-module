package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.EngineType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EngineTypeImplTest {
    private EngineTypeImpl engineTypeImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        engineTypeImpl = new EngineTypeImpl();
        session = SessionManager.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        if (transaction != null) {
            transaction.rollback(); // Откатываем изменения
        }
        session.close();
    }

    private EngineType createTestEngineType() {
        EngineType engineType = new EngineType();
        engineType.setName("Test Engine Type");
        return engineType;
    }

    @Test
    void save_shouldSaveEngineType() {
        EngineType engineType = createTestEngineType();
        engineTypeImpl.save(engineType);

        EngineType fetchedEngineType = engineTypeImpl.getById(engineType.getId());

        assertNotNull(fetchedEngineType);
        assertEquals(engineType.getName(), fetchedEngineType.getName());
    }

    @Test
    void getById_shouldReturnEngineType_whenExists() {
        EngineType engineType = createTestEngineType();
        engineTypeImpl.save(engineType);

        EngineType fetchedEngineType = engineTypeImpl.getById(engineType.getId());

        assertNotNull(fetchedEngineType);
    }

    @Test
    void getById_shouldReturnNull_whenEngineTypeDoesNotExist() {
        EngineType fetchedEngineType = engineTypeImpl.getById(-1); // Несуществующий ID
        assertNull(fetchedEngineType);
    }

    @Test
    void getAll_shouldReturnListOfEngineTypes() {
        List<EngineType> engineTypes = engineTypeImpl.getAll();
        assertNotNull(engineTypes);
    }

    @Test
    void update_shouldUpdateEngineType() {
        EngineType engineType = createTestEngineType();
        engineTypeImpl.save(engineType);

        engineType.setName("Updated Engine Type");
        engineTypeImpl.update(engineType);

        EngineType updatedEngineType = engineTypeImpl.getById(engineType.getId());
        assertEquals("Updated Engine Type", updatedEngineType.getName());
    }

    @Test
    void delete_shouldRemoveEngineType_whenExists() throws CascadeDependencyException {
        EngineType engineType = createTestEngineType();
        engineTypeImpl.save(engineType);

        engineTypeImpl.delete(engineType);
        EngineType deletedEngineType = engineTypeImpl.getById(engineType.getId());

        assertNull(deletedEngineType);
    }

    @Test
    void delete_shouldThrowException_whenEngineTypeHasDependencies() {
        EngineType engineType = engineTypeImpl.getById(1);

        // Макет: Нужно создать ситуацию, при которой удаление вызовет CascadeDependencyException
        assertThrows(CascadeDependencyException.class, () -> {
            engineTypeImpl.delete(engineType);
        });
    }

    @Test
    void deleteByID_shouldRemoveEngineType_whenExists() throws CascadeDependencyException {
        EngineType engineType = createTestEngineType();
        engineTypeImpl.save(engineType);

        engineTypeImpl.deleteByID(engineType.getId());
        EngineType deletedEngineType = engineTypeImpl.getById(engineType.getId());

        assertNull(deletedEngineType);
    }

    @Test
    void deleteByID_shouldThrowException_whenEngineTypeHasDependencies() {
        EngineType engineType = engineTypeImpl.getById(1);

        // Макет для ситуации, когда удаление невозможно из-за зависимостей
        assertThrows(CascadeDependencyException.class, () -> {
            engineTypeImpl.deleteByID(engineType.getId());
        });
    }
}
