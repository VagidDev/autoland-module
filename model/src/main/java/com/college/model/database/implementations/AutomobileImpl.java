package com.college.model.database.implementations;

import com.college.model.entity.Automobile;
import com.college.model.database.interfaces.AutomobileDAO;

/**
 * @author Vagid Zibliuc
 */

public class AutomobileImpl extends AbstractCRUDRepository<Integer, Automobile> implements AutomobileDAO {
    private static final String GET_BY_ID_HQL = "FROM Automobile WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM Automobile";

    @Override
    protected Class<Automobile> getEntityClass() {
        return Automobile.class;
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
