package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.GearboxType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GearboxTypeImplTest {
    private GearboxTypeImpl gearboxTypeImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        gearboxTypeImpl = new GearboxTypeImpl();
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

    private GearboxType createTestGearboxType() {
        GearboxType gearboxType = new GearboxType();
        gearboxType.setName("Test Gearbox Type");
        return gearboxType;
    }

    @Test
    void save_shouldSaveGearboxType() {
        GearboxType gearboxType = createTestGearboxType();
        gearboxTypeImpl.save(gearboxType);

        GearboxType fetchedGearboxType = gearboxTypeImpl.getById(gearboxType.getId());

        assertNotNull(fetchedGearboxType);
        assertEquals(gearboxType.getName(), fetchedGearboxType.getName());
    }

    @Test
    void getById_shouldReturnGearboxType_whenExists() {
        GearboxType gearboxType = createTestGearboxType();
        gearboxTypeImpl.save(gearboxType);

        GearboxType fetchedGearboxType = gearboxTypeImpl.getById(gearboxType.getId());

        assertNotNull(fetchedGearboxType);
    }

    @Test
    void getById_shouldReturnNull_whenGearboxTypeDoesNotExist() {
        GearboxType fetchedGearboxType = gearboxTypeImpl.getById(-1); // Несуществующий ID
        assertNull(fetchedGearboxType);
    }

    @Test
    void getAll_shouldReturnListOfGearboxTypes() {
        List<GearboxType> gearboxTypes = gearboxTypeImpl.getAll();
        assertNotNull(gearboxTypes);
    }

    @Test
    void update_shouldUpdateGearboxType() {
        GearboxType gearboxType = createTestGearboxType();
        gearboxTypeImpl.save(gearboxType);

        gearboxType.setName("Updated Gearbox Type");
        gearboxTypeImpl.update(gearboxType);

        GearboxType updatedGearboxType = gearboxTypeImpl.getById(gearboxType.getId());
        assertEquals("Updated Gearbox Type", updatedGearboxType.getName());
    }

    @Test
    void delete_shouldRemoveGearboxType_whenExists() throws CascadeDependencyException {
        GearboxType gearboxType = createTestGearboxType();
        gearboxTypeImpl.save(gearboxType);

        gearboxTypeImpl.delete(gearboxType);
        GearboxType deletedGearboxType = gearboxTypeImpl.getById(gearboxType.getId());

        assertNull(deletedGearboxType);
    }

    @Test
    void delete_shouldThrowException_whenGearboxTypeHasDependencies() {
        GearboxType gearboxType = gearboxTypeImpl.getById(2);

        // Макет: Нужно создать ситуацию, при которой удаление вызовет CascadeDependencyException
        assertThrows(CascadeDependencyException.class, () -> {
            gearboxTypeImpl.delete(gearboxType);
        });
    }

    @Test
    void deleteByID_shouldRemoveGearboxType_whenExists() throws CascadeDependencyException {
        GearboxType gearboxType = createTestGearboxType();
        gearboxTypeImpl.save(gearboxType);

        gearboxTypeImpl.deleteByID(gearboxType.getId());
        GearboxType deletedGearboxType = gearboxTypeImpl.getById(gearboxType.getId());

        assertNull(deletedGearboxType);
    }

    @Test
    void deleteByID_shouldThrowException_whenGearboxTypeHasDependencies() {
        GearboxType gearboxType = gearboxTypeImpl.getById(2);

        // Макет для ситуации, когда удаление невозможно из-за зависимостей
        assertThrows(CascadeDependencyException.class, () -> {
            gearboxTypeImpl.deleteByID(gearboxType.getId());
        });
    }
}
