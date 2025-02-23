package com.college.model.database.implementations;

import com.college.model.entity.Contract;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.*;
import com.college.model.keys.EquipmentId;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vagid Zibliuc
 */

public class ContractImpl implements ContractDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_contracts WHERE c_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_contracts";
    private static final String INSERT_QUERY = "INSERT INTO au_contracts (c_user_id, c_dealer_id, c_auto_id, c_equip_id, c_warranty_id) "
            + "VALUES(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_contracts\n" +
            "SET c_user_id = ?, c_dealer_id = ?, c_auto_id = ?, c_equip_id = ?, c_warranty_id = ?, c_data = ?\n" +
            "WHERE c_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_contracts WHERE c_id = ?;";


    private final UserDAO userRepository;
    private final DealerDAO dealerRepository;
    private final WarrantyDAO warrantyRepository;
    private final AutomobileDAO automobileRepository;
    private final EquipmentDAO equipmentRepository;

    public ContractImpl(UserDAO userRepository, DealerDAO dealerRepository, WarrantyDAO warrantyRepository, AutomobileDAO automobileRepository, EquipmentDAO equipmentRepository) {
        this.userRepository = userRepository;
        this.dealerRepository = dealerRepository;
        this.warrantyRepository = warrantyRepository;
        this.automobileRepository = automobileRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Contract getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Contract contract = new Contract();
                contract.setId(result.getInt("c_id"));
                contract.setUser(userRepository.getById(result.getInt("c_user_id")));
                contract.setDealer(dealerRepository.getById(result.getInt("c_dealer_id")));
                contract.setAutomobile(automobileRepository.getById(result.getInt("c_auto_id")));
                contract.setEquipment(equipmentRepository.getById(
                        new EquipmentId(
                                result.getInt("c_auto_id"),
                                result.getInt("c_equip_id"))
                        )
                );
                contract.setWarranty(warrantyRepository.getById(result.getInt("c_warranty_id")));
                contract.setConclusionDate(result.getDate("c_data"));
                return contract;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Contract> getAll() {
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            List<Contract> contracts = new ArrayList<>();
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
            while (result.next()) {
                Contract contract = new Contract();
                contract.setId(result.getInt("c_id"));
                contract.setUser(userRepository.getById(result.getInt("c_user_id")));
                contract.setDealer(dealerRepository.getById(result.getInt("c_dealer_id")));
                contract.setAutomobile(automobileRepository.getById(result.getInt("c_auto_id")));
                contract.setEquipment(equipmentRepository.getById(
                        new EquipmentId(
                                result.getInt("c_auto_id"),
                                result.getInt("c_equip_id"))
                        )
                );
                contract.setWarranty(warrantyRepository.getById(result.getInt("c_warranty_id")));
                contract.setConclusionDate(result.getDate("c_data"));
                contracts.add(contract);
            }
            return contracts;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Contract save(Contract t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, t.getUser().getId());
            statement.setInt(2, t.getDealer().getId());
            statement.setInt(3, t.getAutomobile().getId());
            statement.setInt(4, t.getEquipment().getId().getEquipmentId());
            statement.setInt(5, t.getWarranty().getId());

            statement.execute();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                t.setId(keys.getInt(1));
                return t;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Contract t) {
        try (Connection conn = Database.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);

            statement.setInt(1, t.getUser().getId());
            statement.setInt(2, t.getDealer().getId());
            statement.setInt(3, t.getAutomobile().getId());
            statement.setInt(4, t.getEquipment().getId().getEquipmentId());
            statement.setInt(5, t.getWarranty().getId());
            statement.setDate(6, new Date(t.getConclusionDate().getTime()));
            statement.setInt(7, t.getId());

            int result = statement.executeUpdate();

            if (result == 0) {
                throw new EntityNotFoundException("Contract with id=" + t.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Contract t) throws CascadeDependencyException {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, t.getId());
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("SuspensionType with id=" + t.getId() + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteByID(Integer id) throws CascadeDependencyException {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("SuspensionType with id=" + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
