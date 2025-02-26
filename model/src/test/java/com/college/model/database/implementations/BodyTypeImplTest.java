package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.BodyType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BodyTypeImplTest {
    private BodyTypeImpl bodyTypeImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        bodyTypeImpl = new BodyTypeImpl();
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

    private BodyType createTestBodyType() {
        BodyType bodyType = new BodyType();
        bodyType.setName("Test Body Type");
        return bodyType;
    }

    @Test
    void save_shouldSaveBodyType() {
        BodyType bodyType = createTestBodyType();
        bodyTypeImpl.save(bodyType);

        BodyType fetchedBodyType = bodyTypeImpl.getById(bodyType.getId());

        assertNotNull(fetchedBodyType);
        assertEquals(bodyType.getName(), fetchedBodyType.getName());
    }

    @Test
    void getById_shouldReturnBodyType_whenExists() {
        BodyType bodyType = createTestBodyType();
        bodyTypeImpl.save(bodyType);

        BodyType fetchedBodyType = bodyTypeImpl.getById(bodyType.getId());

        assertNotNull(fetchedBodyType);
    }

    @Test
    void getById_shouldReturnNull_whenBodyTypeDoesNotExist() {
        BodyType fetchedBodyType = bodyTypeImpl.getById(-1); // Несуществующий ID
        assertNull(fetchedBodyType);
    }

    @Test
    void getAll_shouldReturnListOfBodyTypes() {
        List<BodyType> bodyTypes = bodyTypeImpl.getAll();
        assertNotNull(bodyTypes);
    }

    @Test
    void update_shouldUpdateBodyType() {
        BodyType bodyType = createTestBodyType();
        bodyTypeImpl.save(bodyType);

        bodyType.setName("Updated Body Type");
        bodyTypeImpl.update(bodyType);

        BodyType updatedBodyType = bodyTypeImpl.getById(bodyType.getId());
        assertEquals("Updated Body Type", updatedBodyType.getName());
    }

    @Test
    void delete_shouldRemoveBodyType_whenExists() throws CascadeDependencyException {
        BodyType bodyType = createTestBodyType();
        bodyTypeImpl.save(bodyType);

        bodyTypeImpl.delete(bodyType);
        BodyType deletedBodyType = bodyTypeImpl.getById(bodyType.getId());

        assertNull(deletedBodyType);
    }

    @Test
    void delete_shouldThrowException_whenBodyTypeHasDependencies() {
        BodyType bodyType = bodyTypeImpl.getById(1);

        // Макет: Нужно создать ситуацию, при которой удаление вызовет CascadeDependencyException
        assertThrows(CascadeDependencyException.class, () -> {
            bodyTypeImpl.delete(bodyType);
        });
    }

    @Test
    void deleteByID_shouldRemoveBodyType_whenExists() throws CascadeDependencyException {
        BodyType bodyType = createTestBodyType();
        bodyTypeImpl.save(bodyType);

        bodyTypeImpl.deleteByID(bodyType.getId());
        BodyType deletedBodyType = bodyTypeImpl.getById(bodyType.getId());

        assertNull(deletedBodyType);
    }

    @Test
    void deleteByID_shouldThrowException_whenBodyTypeHasDependencies() {
        BodyType bodyType = bodyTypeImpl.getById(1);

        // Макет для ситуации, когда удаление невозможно из-за зависимостей
        assertThrows(CascadeDependencyException.class, () -> {
            bodyTypeImpl.deleteByID(bodyType.getId());
        });
    }
}
