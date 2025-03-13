package com.college.model.database.implementations;

import com.college.model.database.SessionManager;
import com.college.model.entity.Automobile;
import com.college.model.entity.Equipment;
import com.college.model.database.interfaces.*;
import com.college.model.entity.keys.EquipmentId;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


/**
 * @author Vagid Zibliuc
 */
public class EquipmentImpl extends AbstractCRUDRepository<EquipmentId, Equipment> implements EquipmentDAO {

    private static final String GET_BY_ID_HQL = "FROM Equipment WHERE id = :id";
    private static final String GET_ALL_HQL = "FROM Equipment";


    private static final String GET_BY_AUTO_HQL = "FROM Equipment e WHERE e.id.automobileId = :autoId";
    private static final String GET_FREE_ID_HQL = "SELECT max(e.id.equipmentId) FROM Equipment e WHERE e.id.automobileId = :autoId";

    public EquipmentImpl() {}

    //better this method create as a stored procedure in database
    private EquipmentId getActualId(int autoId) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Integer> query = session.createQuery(GET_FREE_ID_HQL, Integer.class);
            query.setParameter("autoId", autoId);
            session.getTransaction().commit();
            int equipmentId = query.uniqueResult() + 1;
            return new EquipmentId(autoId, equipmentId);
        }
    }

    @Override
    public List<Equipment> getByAuto(Automobile automobile) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Equipment> query = session.createQuery(GET_BY_AUTO_HQL, Equipment.class);
            query.setParameter("autoId", automobile.getId());
            List<Equipment> equipments = query.list();
            session.getTransaction().commit();
            return equipments;
        }
    }

    //пусть это немного не про SOLID, но это мой косяк в БД
    @Override
    public Equipment save(Equipment equipment) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            Automobile automobile = session.get(Automobile.class, equipment.getAutomobile().getId());
            equipment.setAutomobile(automobile);

            EquipmentId id = getActualId(automobile.getId());
            equipment.setId(id);

            session.persist(equipment);
            session.getTransaction().commit();
            return equipment;
        }
    }

    @Override
    protected Class<Equipment> getEntityClass() {
        return Equipment.class;
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
