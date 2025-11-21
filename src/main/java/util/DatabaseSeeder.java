package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSeeder {

    public static void initialize() {
        Connection conn = DBConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {

            // customers
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS customers (
                      customer_id INTEGER PRIMARY KEY AUTOINCREMENT,
                      full_name TEXT NOT NULL,
                      email TEXT UNIQUE NOT NULL,
                      phone TEXT,
                      created_at TEXT DEFAULT CURRENT_TIMESTAMP
                    );
                    """);

            // accounts
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS accounts (
                      account_id INTEGER PRIMARY KEY AUTOINCREMENT,
                      account_number TEXT UNIQUE NOT NULL,
                      customer_id INTEGER NOT NULL,
                      balance REAL DEFAULT 0.0,
                      account_type TEXT DEFAULT 'SAVINGS',
                      created_at TEXT DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
                    );
                    """);

            // transactions
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS transactions (
                      transaction_id INTEGER PRIMARY KEY AUTOINCREMENT,
                      account_id INTEGER NOT NULL,
                      amount REAL NOT NULL,
                      transaction_type TEXT NOT NULL,
                      description TEXT,
                      created_at TEXT DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
                    );
                    """);

            // users
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS users (
                      user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                      username TEXT UNIQUE NOT NULL,
                      password TEXT NOT NULL,
                      role TEXT NOT NULL
                    );
                    """);

            // seed admin, employee and a sample customer-user (if not exists)
            stmt.execute("""
                INSERT INTO users(username, password, role)
                SELECT 'admin', 'admin123', 'ADMIN'
                WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='admin');
                """);
            stmt.execute("""
                INSERT INTO users(username, password, role)
                SELECT 'emp1', 'pass123', 'EMPLOYEE'
                WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='emp1');
                """);
            stmt.execute("""
                INSERT INTO users(username, password, role)
                SELECT 'cust1', 'cust123', 'CUSTOMER'
                WHERE NOT EXISTS (SELECT 1 FROM users WHERE username='cust1');
                """);

            System.out.println("DB schema ensured and seed users inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
