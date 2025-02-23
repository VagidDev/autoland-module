package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UserImplTest {
    private final UserImpl userRepository = new UserImpl();

    @Test
    void getById_shouldFindUserWithId() {
        User user = userRepository.getById(1);
        assertEquals(1, user.getId());
    }

    @Test
    void getById_shouldNotFindUserWithId() {
        User user = userRepository.getById(9999);
        assertNull(user);
    }

    @Test
    void getAll_shouldReturnAllUsers() {
        List<User> users = userRepository.getAll();
        assertNotNull(users);
    }

    @Test
    void save_shouldBeEqualsToObjectFromDatabase() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("login");
        user.setRole("user");
        user.setName("test1");
        user.setSurname("test1");
        user.setBirthday(new Date());
        user.setEmail("mail@gmail.com");
        user.setTelephone("+37367292196");
        user.setAddress("some address");
        user.setAvatar("images/default.png");

        User savedUser = userRepository.save(user);

        if (Objects.nonNull(savedUser)) {
            try (Session session = SessionManager.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.remove(savedUser);
                transaction.commit();
            }
        }

        assertNotNull(savedUser);

    }

    @Test
    void update_shouldChangeObjectFromDatabase() {
        User originalUser = userRepository.getById(15);
        User user = userRepository.getById(15);
        user.setName("another name");

        userRepository.update(user);

        user = userRepository.getById(15);

        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(originalUser);
            transaction.commit();
        }

        assertEquals("another name", user.getName());
    }

    @Test
    void getByLoginAndPassword_shouldReturnUser() {
        String login = "vaxa";
        String password = "admin";
        User user = userRepository.getByLoginAndPassword(login, password);

        assertNotNull(user);
    }

    @Test
    void delete_shouldDeleteObjectFromDatabase() throws CascadeDependencyException {
        User user = new User();
        user.setLogin("login");
        user.setPassword("login");
        user.setRole("user");
        user.setName("test1");
        user.setSurname("test1");
        user.setBirthday(new Date());
        user.setEmail("mail@gmail.com");
        user.setTelephone("+37367292196");
        user.setAddress("some address");
        user.setAvatar("images/default.png");
        User savedUser = userRepository.save(user);

        userRepository.delete(savedUser);

        User anotherUser = userRepository.getById(savedUser.getId());
        assertNull(anotherUser);
    }

    @Test
    void delete_shouldDeleteObjectFromDatabaseById() throws CascadeDependencyException {
        User user = new User();
        user.setLogin("login");
        user.setPassword("login");
        user.setRole("user");
        user.setName("test1");
        user.setSurname("test1");
        user.setBirthday(new Date());
        user.setEmail("mail@gmail.com");
        user.setTelephone("+37367292196");
        user.setAddress("some address");
        user.setAvatar("images/default.png");
        User savedUser = userRepository.save(user);

        userRepository.deleteByID(savedUser.getId());

        User anotherUser = userRepository.getById(savedUser.getId());
        assertNull(anotherUser);
    }

    @Test
    void delete_shouldNotDeleteObjectFromDatabase() {
        User user = userRepository.getById(10);

        assertThrows(CascadeDependencyException.class, () -> userRepository.delete(user));
    }

    @Test
    void getByLoginAndPassword_shouldNotReturnUser() {
        String login = "1234";
        String password = "546238";
        User user = userRepository.getByLoginAndPassword(login, password);

        assertNull(user);
    }

    @Test
    void ifUserExists_exists() {
        String login = "vaxa";
        boolean result = userRepository.ifUserExists(login);
        assertTrue(result);
    }

    @Test
    void ifUserExists_doesNotExists() {
        String login = "qwertyuioplkhggff";
        boolean result = userRepository.ifUserExists(login);
        assertFalse(result);
    }
}

