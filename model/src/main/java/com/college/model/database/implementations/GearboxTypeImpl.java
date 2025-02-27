package com.college.model.database.implementations;

import com.college.model.entity.GearboxType;
import com.college.model.database.interfaces.GearboxTypeDAO;

public class GearboxTypeImpl extends AbstractCRUDRepository<Integer, GearboxType> implements GearboxTypeDAO {
    private static final String GET_BY_ID_HQL = "FROM GearboxType WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM GearboxType";


    @Override
    protected Class<GearboxType> getEntityClass() {
        return GearboxType.class;
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
