package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String URL = "jdbc:mysql://localhost/bank?serverTimezone=UTC&useSSL=false";
    private static final String UID = "newuser";
    private static final String PWD = "newuser";


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, UID, PWD);

        } catch (SQLException e) {
            System.err.println("Something went wrong with getConnection()");
        }
        return null;
    }
}
