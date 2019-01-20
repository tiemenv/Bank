package main.database;

import main.model.Account;
import main.model.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MySqlBankRepo implements BankRepo {
    private static final String SQL_GET_OWNERS = "select* from persons";
    private static final String SQL_GET_ACCOUNTS = "select* from accounts where owner=?";
    private static final String SQL_UPDATE_ACCOUNT = "UPDATE accounts SET balance=? WHERE iban=?";
    private static final String SQL_GET_ACCOUNT = "select* from accounts where iban=?";


    private Owner createOwner(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String id = rs.getString("id");
        return new Owner(name, id);
    }

    private Account createAccount(ResultSet rs) throws SQLException {
        String ibanNr = rs.getString("iban");
        Double balance = rs.getDouble("balance");
        return new Account(ibanNr, balance);
    }


    @Override
    public List<Owner> getOwners() {
        try (PreparedStatement prep = MySqlConnection.getConnection().prepareStatement(SQL_GET_OWNERS)) {
            try (ResultSet rs = prep.executeQuery()) {
                List<Owner> owners = new ArrayList<>();

                while (rs.next()) {
                    owners.add(createOwner(rs));
                }
                return owners;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong with getOwners");
        }
        return null;
    }

    @Override
    public List<Account> getAccounts(Owner owner) {
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement prep = connection.prepareStatement(SQL_GET_ACCOUNTS)
        ) {
            prep.setString(1, owner.getId());
            try (ResultSet rs = prep.executeQuery()) {
                List<Account> accounts = new ArrayList<>();

                while (rs.next()) {
                    accounts.add(createAccount(rs));
                }
                return accounts;
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong with getAccounts");
        }
        return null;
    }

    @Override
    public Account getAccount(Account account) {
        try (
                Connection connection = MySqlConnection.getConnection();
                PreparedStatement prep = connection.prepareStatement(SQL_GET_ACCOUNT)
        ) {
            prep.setString(1, account.getAccountNr());
            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next()){
                    return createAccount(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong with getAccounts");
        }
        return null;
    }

    @Override
    public void updateAccount(Account account, double amount) {
        // TODO: 19/01/2019 BUG! enkele laatste action gaat ervan
        try (PreparedStatement prep = MySqlConnection.getConnection().prepareStatement(SQL_UPDATE_ACCOUNT)) {
            prep.setDouble(1, account.getAmount() + amount);
            prep.setString(2, account.getAccountNr());

            prep.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Something went wrong with updateAccount");
        }

    }
}
