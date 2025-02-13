package com.banking.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/onlinebanking"; // Update with your database name
    private static final String USER = "root"; // Update with your DB username
    private static final String PASSWORD = "root"; // Update with your DB password

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the appropriate driver class for your database
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL Driver not found.");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
