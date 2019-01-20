package main.model;

public class Account {

    private String accountNr;
    private Double amount;

    public Account(String accountNr, Double amount) {
        this.accountNr = accountNr;
        this.amount = amount;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public Double getAmount() {
        return amount;
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            this.amount -= amount;
        } else {
            System.out.println("amount is te klein => " + amount);
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.amount += amount;
        } else {
            System.out.println("amount is te klein => " + amount);
        }
    }

    @Override
    public String toString() {
        return accountNr + "=>"+ amount;
    }
}
