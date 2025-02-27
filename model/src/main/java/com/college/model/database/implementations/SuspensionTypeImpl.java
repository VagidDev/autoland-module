package com.college.model.database.implementations;

import com.college.model.entity.SuspensionType;
import com.college.model.database.interfaces.SuspensionTypeDAO;

public class SuspensionTypeImpl extends AbstractCRUDRepository<Integer, SuspensionType> implements SuspensionTypeDAO {
    private static final String GET_BY_ID_HQL = "FROM SuspensionType WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM SuspensionType";

    @Override
    protected Class<SuspensionType> getEntityClass() {
        return SuspensionType.class;
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
