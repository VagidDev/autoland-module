package com.college.model.database.implementations;

import com.college.model.entity.BodyType;
import com.college.model.database.interfaces.BodyTypeDAO;


public class BodyTypeImpl extends AbstractCRUDRepository<Integer, BodyType> implements BodyTypeDAO {
    private final static String GET_BY_ID_HQL = "FROM BodyType WHERE id = :id";
    private final static String GET_ALL_HQL = "FROM BodyType";

    @Override
    protected Class<BodyType> getEntityClass() {
        return BodyType.class;
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
