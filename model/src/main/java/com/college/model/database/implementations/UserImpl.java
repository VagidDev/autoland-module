/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.implementations;

import com.college.model.entity.User;
import com.college.model.database.Database;
import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.interfaces.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class UserImpl extends AbstractCRUDRepository<Integer, User> implements UserDAO {

    //Hibernate queries
    private static final String GET_BY_ID_HQL = "FROM User WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM User";
    private static final String SEARCH_BY_LOGIN_AND_PASSWORD_HQL = "FROM User WHERE login = :login AND password = :password";
    private static final String USER_EXISTS_HQL = "FROM User WHERE login = :login";
    

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected String getByIdQuery() {
        return GET_BY_ID_HQL;
    }

    @Override
    protected String getAllQuery() {
        return GET_ALL_HQL;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> getByCredentials = session.createQuery(SEARCH_BY_LOGIN_AND_PASSWORD_HQL, User.class);
            getByCredentials.setParameter("login", login);
            getByCredentials.setParameter("password", password);
            User user = getByCredentials.stream().findFirst().orElse(null);
            transaction.commit();
            return user;
        }
    }

    @Override
    public boolean ifUserExists(String login) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            boolean result = session
                    .createQuery(USER_EXISTS_HQL, User.class)
                    .setParameter("login", login)
                    .getResultList()
                    .isEmpty();
            transaction.commit();
            return !result;
        }
    }
}
