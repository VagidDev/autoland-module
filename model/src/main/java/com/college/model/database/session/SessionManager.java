package com.college.model.database.session;

/**
 * @author Vagid Zibliuc
 */

public final class SessionManager {
    private static final Session session = new Session();

    public static Session getSession() {
        return session;
    }

    private SessionManager() {}
}
