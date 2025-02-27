package com.college.model.database.implementations;

import com.college.model.entity.Warranty;
import com.college.model.database.interfaces.WarrantyDAO;

/**
 *
 * @author Vagid Zibliuc
 */
public class WarrantyImpl extends AbstractCRUDRepository<Integer, Warranty> implements WarrantyDAO {
    private static final String GET_BY_ID_HQL = "FROM Warranty WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM Warranty";


    @Override
    protected Class<Warranty> getEntityClass() {
        return Warranty.class;
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
