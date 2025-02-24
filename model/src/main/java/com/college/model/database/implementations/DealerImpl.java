package com.college.model.database.implementations;

import com.college.model.entity.Dealer;
import com.college.model.database.interfaces.DealerDAO;

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
