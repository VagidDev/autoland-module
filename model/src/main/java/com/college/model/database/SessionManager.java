package com.college.model.database;

import com.college.model.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public final class SessionManager {
    private static SessionFactory sessionFactory;

    private static SessionFactory configuredSessionFactory() {
        return new Configuration()
                //add package didn't works, so i add classes manually
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Dealer.class)
                .addAnnotatedClass(BodyType.class)
                .addAnnotatedClass(DriveType.class)
                .addAnnotatedClass(EngineType.class)
                .addAnnotatedClass(FuelType.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = configuredSessionFactory();
        }
        return sessionFactory;
    }
}
