package main.database;

import java.sql.SQLException;

public class BankException extends Throwable {
    public BankException(String s, SQLException ex) {
        super(s, ex);
    }
}
