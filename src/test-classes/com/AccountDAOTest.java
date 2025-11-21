package test.java;


import model.Account;
import org.junit.jupiter.api.*;
import util.DBConnection;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AccountDAOTest {

    private static Connection conn;
    private static AccountDAO accountDAO;

    @BeforeAll
    static void setup() throws Exception {
        conn = DBConnection.connectInMemory(); // custom method for testing
        accountDAO = new AccountDAO(conn);

        // Create tables for testing
        conn.createStatement().execute(
                "CREATE TABLE accounts (" +
                "account_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account_number TEXT," +
                "balance REAL);"
        );
    }

    @Test
    void testInsertAccount() throws Exception {
        Account acc = new Account("12345", 200.0);
        assertTrue(accountDAO.insert(acc));
    }

    @Test
    void testFindAccount() throws Exception {
        Account acc = new Account("67890", 300.0);
        accountDAO.insert(acc);

        Account found = accountDAO.findByAccountNumber("67890");
        assertNotNull(found);
        assertEquals(300.0, found.getBalance());
    }
}

