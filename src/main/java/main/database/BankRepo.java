package main.database;

import main.model.Account;
import main.model.Owner;

import java.util.List;

public interface BankRepo {

    List<Owner> getOwners() throws BankException;
    List<Account> getAccounts(Owner owner) throws BankException;
    void updateAccount(Account account, double amount) throws BankException;
    Account getAccount(Account account);

}
