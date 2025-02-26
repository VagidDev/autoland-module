package com.college.model.database.implementations;

import com.college.model.entity.FuelType;
import com.college.model.database.interfaces.FuelTypeDAO;

public class FuelTypeImpl extends AbstractCRUDRepository<Integer, FuelType> implements FuelTypeDAO {
    private static final String GET_BY_ID_HQL = "FROM FuelType WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM FuelType";

    @Override
    protected Class<FuelType> getEntityClass() {
        return FuelType.class;
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
