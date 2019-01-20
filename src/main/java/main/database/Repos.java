package main.database;

public class Repos {
    private static Repos ourInstance = new Repos();

    private Repos() {

    }

    public static Repos getInstance() {
        return ourInstance;
    }

    public synchronized BankRepo getBankRepo() {
        return new MySqlBankRepo();
    }
}
