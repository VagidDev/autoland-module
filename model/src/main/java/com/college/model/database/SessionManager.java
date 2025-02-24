package com.college.model.database;

import com.college.model.entity.Dealer;
import com.college.model.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class SessionManager {
    private static SessionFactory sessionFactory;

    private static SessionFactory configuredSessionFactory() {
        return new Configuration()
                //add package didn't works, so i add classes manually
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Dealer.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = configuredSessionFactory();
        }
        return sessionFactory;
    }
}
