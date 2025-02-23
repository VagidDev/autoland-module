package com.college.model.database;

import com.college.model.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class SessionManager {
    private static SessionFactory sessionFactory;

    private static SessionFactory configuredSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = configuredSessionFactory();
        }
        return sessionFactory;
    }
}
