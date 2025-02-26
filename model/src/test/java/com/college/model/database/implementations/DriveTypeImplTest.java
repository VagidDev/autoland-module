package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.DriveType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DriveTypeImplTest {
    private DriveTypeImpl driveTypeImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        driveTypeImpl = new DriveTypeImpl();
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

    private DriveType createTestDriveType() {
        DriveType driveType = new DriveType();
        driveType.setName("Test Drive Type");
        return driveType;
    }

    @Test
    void save_shouldSaveDriveType() {
        DriveType driveType = createTestDriveType();
        driveTypeImpl.save(driveType);

        DriveType fetchedDriveType = driveTypeImpl.getById(driveType.getId());

        assertNotNull(fetchedDriveType);
        assertEquals(driveType.getName(), fetchedDriveType.getName());
    }

    @Test
    void getById_shouldReturnDriveType_whenExists() {
        DriveType driveType = createTestDriveType();
        driveTypeImpl.save(driveType);

        DriveType fetchedDriveType = driveTypeImpl.getById(driveType.getId());

        assertNotNull(fetchedDriveType);
    }

    @Test
    void getById_shouldReturnNull_whenDriveTypeDoesNotExist() {
        DriveType fetchedDriveType = driveTypeImpl.getById(-1); // Несуществующий ID
        assertNull(fetchedDriveType);
    }

    @Test
    void getAll_shouldReturnListOfDriveTypes() {
        List<DriveType> driveTypes = driveTypeImpl.getAll();
        assertNotNull(driveTypes);
    }

    @Test
    void update_shouldUpdateDriveType() {
        DriveType driveType = createTestDriveType();
        driveTypeImpl.save(driveType);

        driveType.setName("Updated Drive Type");
        driveTypeImpl.update(driveType);

        DriveType updatedDriveType = driveTypeImpl.getById(driveType.getId());
        assertEquals("Updated Drive Type", updatedDriveType.getName());
    }

    @Test
    void delete_shouldRemoveDriveType_whenExists() throws CascadeDependencyException {
        DriveType driveType = createTestDriveType();
        driveTypeImpl.save(driveType);

        driveTypeImpl.delete(driveType);
        DriveType deletedDriveType = driveTypeImpl.getById(driveType.getId());

        assertNull(deletedDriveType);
    }

    @Test
    void delete_shouldThrowException_whenDriveTypeHasDependencies() {
        DriveType driveType = driveTypeImpl.getById(1);

        // Макет: Нужно создать ситуацию, при которой удаление вызовет CascadeDependencyException
        assertThrows(CascadeDependencyException.class, () -> {
            driveTypeImpl.delete(driveType);
        });
    }

    @Test
    void deleteByID_shouldRemoveDriveType_whenExists() throws CascadeDependencyException {
        DriveType driveType = createTestDriveType();
        driveTypeImpl.save(driveType);

        driveTypeImpl.deleteByID(driveType.getId());
        DriveType deletedDriveType = driveTypeImpl.getById(driveType.getId());

        assertNull(deletedDriveType);
    }

    @Test
    void deleteByID_shouldThrowException_whenDriveTypeHasDependencies() {
        DriveType driveType = driveTypeImpl.getById(1);

        // Макет для ситуации, когда удаление невозможно из-за зависимостей
        assertThrows(CascadeDependencyException.class, () -> {
            driveTypeImpl.deleteByID(driveType.getId());
        });
    }
}
