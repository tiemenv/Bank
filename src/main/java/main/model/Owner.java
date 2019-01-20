package main.model;

import java.util.ArrayList;
import java.util.List;

public class Owner {

    private String name;
    private  String id;
    private List<Account> accounts;

    public Owner(String name, String id) {
        this.name = name;
        this.id = id;
        this.accounts = new ArrayList<>();
    }

    // GETTERS AND SETTERS

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void addAllAccounts(Iterable<Account> accounts) {
        for (Account account : accounts)
            this.accounts.add(account);
        //accounts.forEach(account -> this.accounts.add(account));
        //accounts.forEach(this.accounts::add);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    // FUNCTIONALITY

    public void withdraw(Account sender, double amount) {
        sender.withdraw(amount);
    }

    public void deposit(Account target, double amount) {
        target.deposit(amount);
    }

    public void Transfer(Account sender, Account target, double amount) {
        sender.withdraw(amount);
        target.deposit(amount);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
