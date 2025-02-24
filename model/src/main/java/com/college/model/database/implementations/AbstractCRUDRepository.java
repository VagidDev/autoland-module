package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.interfaces.CRUDRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class AbstractCRUDRepository<ID,T> implements CRUDRepository<ID,T> {
    @Override
    public T getById(ID id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<T> getQuery = session.createQuery(getByIdQuery(), getEntityClass());
            getQuery.setParameter("id", id);
            T entity = getQuery.uniqueResult();
            transaction.commit();
            return entity;
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<T> getAllQuery = session.createQuery(getAllQuery(), getEntityClass());
            List<T> users = getAllQuery.getResultList();
            transaction.commit();
            return users;
        }
    }

    @Override
    public T save(T t) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(T t) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T t) throws CascadeDependencyException {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new CascadeDependencyException("Cannot delete this row, because it is using in other table");
        }
    }

    @Override
    public void deleteByID(ID id) throws CascadeDependencyException {
        delete(getById(id));
    }

    protected abstract Class<T> getEntityClass();

    protected abstract String getByIdQuery();

    protected abstract String getAllQuery();
}
