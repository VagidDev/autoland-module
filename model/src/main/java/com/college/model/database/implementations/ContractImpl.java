package com.college.model.database.implementations;

import com.college.model.entity.Contract;
import com.college.model.database.interfaces.*;

/**
 * @author Vagid Zibliuc
 */

public class ContractImpl extends AbstractCRUDRepository<Integer, Contract> implements ContractDAO {

    private static final String GET_BY_ID_HQL = "FROM Contract WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM Contract";

    @Override
    protected Class<Contract> getEntityClass() {
        return Contract.class;
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
