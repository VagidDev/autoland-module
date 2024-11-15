/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.college.model.database.implementations;

import com.college.model.Contract;
import com.college.model.database.Database;
import com.college.model.database.interfaces.*;
import com.college.model.keys.EquipmentId;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vagid Zibliuc
 */
public class ContractImpl implements ContractDAO {

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
            String query = "SELECT * FROM au_contract WHERE c_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Contract contract = new Contract();
                contract.setId(result.getInt("c_id"));
                contract.setUser(userRepository.getById(result.getInt("c_user_id")));
                contract.setDealer(dealerRepository.getById(result.getInt("c_dealer_id")));
                contract.setAutomobile(automobileRepository.getById(result.getInt("c_auto_id")));
                contract.setEquipment(equipmentRepository.getById(new EquipmentId(result.getInt("c_auto_id"), result.getInt("c_equip_id"))));
                contract.setWarranty(warrantyRepository.getById(result.getInt("c_warranty_id")));
                contract.setConclusionDate(result.getDate("c_data"));
                return contract;
            }
            throw new RuntimeException("Contract not found!");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Contract> getAll() {
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            List<Contract> contracts = new ArrayList();
            String query = "SELECT * FROM au_contract";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Contract contract = new Contract();
                contract.setId(result.getInt("c_id"));
                contract.setUser(userRepository.getById(result.getInt("c_user_id")));
                contract.setDealer(dealerRepository.getById(result.getInt("c_dealer_id")));
                contract.setAutomobile(automobileRepository.getById(result.getInt("c_auto_id")));
                contract.setEquipment(equipmentRepository.getById(new EquipmentId(result.getInt("c_auto_id"), result.getInt("c_equip_id"))));
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
    public boolean save(Contract t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Contract t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Contract t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
