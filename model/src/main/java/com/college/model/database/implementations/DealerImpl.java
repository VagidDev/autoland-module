package com.college.model.database.implementations;

import com.college.model.entity.Dealer;
import com.college.model.database.Database;
import com.college.model.database.exceptions.CascadeDependencyException;
import com.college.model.database.exceptions.EntityNotFoundException;
import com.college.model.database.interfaces.DealerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vagid Zibliuc
 */

public class DealerImpl implements DealerDAO {
    private static final String GET_BY_ID_QUERY = "SELECT * FROM au_dealers WHERE d_id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM au_dealers";
    private static final String INSERT_QUERY = "INSERT INTO au_dealers (d_name, d_address, d_telephone, d_fax)\n"
            + "VALUES(?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE au_dealers\n" +
            "SET d_name = ?, d_address = ?, d_telephone = ?, d_fax = ?\n" +
            "WHERE d_id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM au_dealers WHERE d_id = ?";

    @Override
    public Dealer getById(Integer id) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Dealer dealer = new Dealer();
                dealer.setId(result.getInt("d_id"));
                dealer.setName(result.getString("d_name"));
                dealer.setAddress(result.getString("d_address"));
                dealer.setTelephone(result.getString("d_telephone"));
                dealer.setFax(result.getString("d_fax"));
                return dealer;
            }
            return null;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Dealer> getAll() {
        List<Dealer> dealers = new ArrayList();
        try (Connection conn = Database.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL_QUERY);
            while (result.next()) {
                Dealer dealer = new Dealer();
                dealer.setId(result.getInt("d_id"));
                dealer.setName(result.getString("d_name"));
                dealer.setAddress(result.getString("d_address"));
                dealer.setTelephone(result.getString("d_telephone"));
                dealer.setFax(result.getString("d_fax"));
                dealers.add(dealer);
            }
            return dealers;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Dealer save(Dealer t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, t.getName());
            statement.setString(2, t.getAddress());
            statement.setString(3, t.getTelephone());
            statement.setString(4, t.getFax());

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
    public void update(Dealer t) {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);

            statement.setString(1, t.getName());
            statement.setString(2, t.getAddress());
            statement.setString(3, t.getTelephone());
            statement.setString(4, t.getFax());
            statement.setInt(5, t.getId());

            int result = statement.executeUpdate();

            if (result == 0) {
                throw new EntityNotFoundException("Dealer with id=" + t.getId() + " did not update, because it does not exists!");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Dealer t) throws CascadeDependencyException {
        try (Connection conn = Database.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, t.getId());
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new CascadeDependencyException("Dealer with id=" + t.getId() + " using in other tables");
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
            throw new CascadeDependencyException("Dealer with id " + id + " using in other tables");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
