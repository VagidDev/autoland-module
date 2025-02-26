package com.college.model.database.implementations;

import com.college.model.entity.DriveType;
import com.college.model.database.interfaces.DriveTypeDAO;

public class DriveTypeImpl extends AbstractCRUDRepository<Integer, DriveType> implements DriveTypeDAO {
    private static final String GET_BY_ID_HQL = "FROM DriveType WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM DriveType";

    @Override
    protected Class<DriveType> getEntityClass() {
        return DriveType.class;
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
