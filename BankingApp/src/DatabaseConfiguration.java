package com.banking.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Configuration class for JDBC connection.
 * Assumes a running MySQL instance (like XAMPP/WAMP/MAMP) and a database named 'banking_system'.
 */
public class DatabaseConfig {

    // IMPORTANT: Change these values if your database configuration is different
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    /**
     * Establishes and returns a new database connection.
     * @return A valid Connection object.
     * @throws SQLException if connection fails.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ CRITICAL ERROR: MySQL JDBC Driver not found. Ensure 'mysql-connector-j.jar' is in your classpath.");
            throw new SQLException("Database driver missing.", e);
        }

        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}

