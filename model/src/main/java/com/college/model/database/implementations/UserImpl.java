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
public class UserImpl implements UserDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_users WHERE u_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_users";
    private static final String INSERT_QUERY = "INSERT INTO au_users (u_login, u_password, u_name, u_surname, u_birthday, u_email, u_telephone, u_address) "
                                                + "VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_users\n"
                                                + "SET u_login = ?, u_password = ?, u_name = ?, u_surname = ?, u_birthday = ?, u_email = ?, u_telephone = ?, u_address = ?, u_avatar = ?\n"
                                                + "WHERE u_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_users WHERE u_id = ?;";
    private static final String SEARCH_BY_LOGIN_AND_PASSWORD = "SELECT * FROM au_users WHERE u_login = ? AND u_password = ?";
    private static final String USER_EXISTS_QUERY = "SELECT * FROM au_users WHERE u_login = ?";
    //Hibernate queries
    private static final String GET_BY_ID_HQL = "FROM User WHERE id = :userid";
    private static final String GET_ALL_HQL = "FROM User";
    private static final String SEARCH_BY_LOGIN_AND_PASSWORD_HQL = "FROM User WHERE login = :login AND password = :password";
    private static final String USER_EXISTS_HQL = "FROM User WHERE login = :login";
    
    @Override
    public User getById(Integer id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> getQuery = session.createQuery(GET_BY_ID_HQL, User.class);
            getQuery.setParameter("userid", id);
            User user = getQuery.stream().findFirst().orElse(null);
            transaction.commit();
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> getAllQuery = session.createQuery(GET_ALL_HQL, User.class);
            List<User> users = getAllQuery.getResultList();
            transaction.commit();
            return users;
        }
    }

    @Override
    public User save(User t) {
        Session session = SessionManager.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User t) {
        Session session = SessionManager.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(User t) throws CascadeDependencyException {
        Session session = SessionManager.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CascadeDependencyException("Cannot delete user with id=" + t.getId() + ", because it is using in other table");
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteByID(Integer id) throws CascadeDependencyException {
        User userToDelete = getById(id);
        delete(userToDelete);
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
