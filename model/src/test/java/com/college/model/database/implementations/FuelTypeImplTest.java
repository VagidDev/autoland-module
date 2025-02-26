package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.FuelType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FuelTypeImplTest {
    private FuelTypeImpl fuelTypeImpl;
    private Session session;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        fuelTypeImpl = new FuelTypeImpl();
        session = SessionManager.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    @AfterEach
    void tearDown() {
        if (transaction != null) {
            transaction.rollback(); // Откатываем изменения после теста
        }
        session.close();
    }

    private FuelType createTestFuelType() {
        FuelType fuelType = new FuelType();
        fuelType.setName("Test Fuel Type");
        return fuelType;
    }

    @Test
    void save_shouldSaveFuelType() {
        FuelType fuelType = createTestFuelType();
        fuelTypeImpl.save(fuelType);

        FuelType fetchedFuelType = fuelTypeImpl.getById(fuelType.getId());

        assertNotNull(fetchedFuelType);
        assertEquals(fuelType.getName(), fetchedFuelType.getName());
    }

    @Test
    void getById_shouldReturnFuelType_whenExists() {
        FuelType fuelType = createTestFuelType();
        fuelTypeImpl.save(fuelType);

        FuelType fetchedFuelType = fuelTypeImpl.getById(fuelType.getId());

        assertNotNull(fetchedFuelType);
    }

    @Test
    void getById_shouldReturnNull_whenFuelTypeDoesNotExist() {
        FuelType fetchedFuelType = fuelTypeImpl.getById(-1); // Несуществующий ID
        assertNull(fetchedFuelType);
    }

    @Test
    void getAll_shouldReturnListOfFuelTypes() {
        List<FuelType> fuelTypes = fuelTypeImpl.getAll();
        assertNotNull(fuelTypes);
    }

    @Test
    void update_shouldUpdateFuelType() {
        FuelType fuelType = createTestFuelType();
        fuelTypeImpl.save(fuelType);

        fuelType.setName("Updated Fuel Type");
        fuelTypeImpl.update(fuelType);

        FuelType updatedFuelType = fuelTypeImpl.getById(fuelType.getId());
        assertEquals("Updated Fuel Type", updatedFuelType.getName());
    }

    @Test
    void delete_shouldRemoveFuelType_whenExists() throws CascadeDependencyException {
        FuelType fuelType = createTestFuelType();
        fuelTypeImpl.save(fuelType);

        fuelTypeImpl.delete(fuelType);
        FuelType deletedFuelType = fuelTypeImpl.getById(fuelType.getId());

        assertNull(deletedFuelType);
    }

    @Test
    void delete_shouldThrowException_whenFuelTypeHasDependencies() {
        FuelType fuelType = fuelTypeImpl.getById(1);

        // Макет: Нужно создать ситуацию, при которой удаление вызовет CascadeDependencyException
        assertThrows(CascadeDependencyException.class, () -> {
            fuelTypeImpl.delete(fuelType);
        });
    }

    @Test
    void deleteByID_shouldRemoveFuelType_whenExists() throws CascadeDependencyException {
        FuelType fuelType = createTestFuelType();
        fuelTypeImpl.save(fuelType);

        fuelTypeImpl.deleteByID(fuelType.getId());
        FuelType deletedFuelType = fuelTypeImpl.getById(fuelType.getId());

        assertNull(deletedFuelType);
    }

    @Test
    void deleteByID_shouldThrowException_whenFuelTypeHasDependencies() {
        FuelType fuelType = fuelTypeImpl.getById(1);

        // Макет для ситуации, когда удаление невозможно из-за зависимостей
        assertThrows(CascadeDependencyException.class, () -> {
            fuelTypeImpl.deleteByID(fuelType.getId());
        });
    }
}
