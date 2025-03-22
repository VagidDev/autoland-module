package com.college.model.database.session;

/**
 * @author Vagid Zibliuc
 */

public final class SessionManager {
    private static final Session session = new Session();

    public static Session getSession() {
        return session;
    }

    public static boolean connect() {
        org.hibernate.Session tempSession = com.college.model.database.SessionManager.getSessionFactory().openSession();
        boolean result = tempSession.isConnected();
        tempSession.close();
        return result;
    }

    private SessionManager() {}
}
