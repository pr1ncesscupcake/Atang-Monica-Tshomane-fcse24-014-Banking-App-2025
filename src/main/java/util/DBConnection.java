package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:bank.db";
    private static Connection conn = null;

    public static synchronized Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL);
                // enable foreign keys for SQLite
                conn.createStatement().execute("PRAGMA foreign_keys = ON");
                System.out.println("Connected to SQLite DB at " + URL);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to connect to DB: " + e.getMessage(), e);
            }
        }
        return conn;
    }
}
