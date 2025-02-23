package com.college.controller;

import com.college.model.entity.User;
import com.college.model.database.session.SessionManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationControllerTest {

    @Test
    void shouldBeUserWithLoginAndPassword() {
        AuthorizationController authorizationController = new AuthorizationController(SessionManager.getSession().getUserRepository());
        boolean res = authorizationController.login("vaxa", "admin");
        assertTrue(res);
    }

    @Test
    void shouldNotBeUserWithLoginAndPassword() {
        AuthorizationController authorizationController = new AuthorizationController(SessionManager.getSession().getUserRepository());
        boolean res = authorizationController.login("qwertwiuq", "1231436");
        assertFalse(res);
    }

    @Test
    void shouldBeLoggedIn() {
        AuthorizationController authorizationController = new AuthorizationController(SessionManager.getSession().getUserRepository());
        authorizationController.login("vaxa", "admin");
        User user = authorizationController.getCurrentUser();
        assertNotNull(user);
    }

    @Test
    void shouldNotBeLoggedIn() {
        AuthorizationController authorizationController = new AuthorizationController(SessionManager.getSession().getUserRepository());
        authorizationController.login("qwertwiuq", "1231436");
        User user = authorizationController.getCurrentUser();
        assertNull(user);
    }

    @Test
    void shouldBeLoggedOut() {
        AuthorizationController authorizationController = new AuthorizationController(SessionManager.getSession().getUserRepository());
        authorizationController.login("vaxa", "admin");
        authorizationController.logout();
        User user = authorizationController.getCurrentUser();
        assertNull(user);
    }

}