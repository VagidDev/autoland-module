package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.entity.Dealer;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.DealerDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vagid Zibliuc
 */

public class DealerImpl extends AbstractCRUDRepository<Integer, Dealer> implements DealerDAO {
    private static final String GET_BY_ID_HQL = "FROM Dealer WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM Dealer";

    @Override
    protected Class<Dealer> getEntityClass() {
        return Dealer.class;
    }

    @Override
    protected String getByIdQuery() {
        return GET_BY_ID_HQL;
    }

    @Override
    protected String getAllQuery() {
        return GET_ALL_HQL;
    }
}
