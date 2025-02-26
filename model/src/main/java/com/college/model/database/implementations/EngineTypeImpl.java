package com.college.model.database.implementations;

import com.college.model.entity.EngineType;
import com.college.model.database.interfaces.EngineTypeDAO;

public class EngineTypeImpl extends AbstractCRUDRepository<Integer, EngineType> implements EngineTypeDAO {
    private static final String GET_BY_ID_HQL = "FROM EngineType WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM EngineType";

    @Override
    protected Class<EngineType> getEntityClass() {
        return EngineType.class;
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
