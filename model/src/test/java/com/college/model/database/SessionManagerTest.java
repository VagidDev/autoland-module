package com.college.model.database;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {
    @Test
    void shouldCreateSessionFactory() {
        SessionFactory factory = SessionManager.getSessionFactory();
        assertNotNull(factory);
    }
}