package com.college.controller;

import com.college.model.User;
import com.college.model.database.session.SessionManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void shouldBeUserWithLoginAndPassword() {
        UserController userController = new UserController(SessionManager.getSession().getUserRepository());
        boolean res = userController.login("vaxa", "admin");
        assertTrue(res);
    }

    @Test
    void shouldNotBeUserWithLoginAndPassword() {
        UserController userController = new UserController(SessionManager.getSession().getUserRepository());
        boolean res = userController.login("qwertwiuq", "1231436");
        assertFalse(res);
    }

    @Test
    void shouldBeLoggedIn() {
        UserController userController = new UserController(SessionManager.getSession().getUserRepository());
        userController.login("vaxa", "admin");
        User user = userController.getCurrentUser();
        assertNotNull(user);
    }

    @Test
    void shouldNotBeLoggedIn() {
        UserController userController = new UserController(SessionManager.getSession().getUserRepository());
        userController.login("qwertwiuq", "1231436");
        User user = userController.getCurrentUser();
        assertNull(user);
    }

    @Test
    void shouldBeLoggedOut() {
        UserController userController = new UserController(SessionManager.getSession().getUserRepository());
        userController.login("vaxa", "admin");
        userController.logout();
        User user = userController.getCurrentUser();
        assertNull(user);
    }

}