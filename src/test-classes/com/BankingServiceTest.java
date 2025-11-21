package test.java;


import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankingServiceTest {

    private BankingService bankingService;

    @BeforeEach
    void setup() {
        bankingService = new BankingService();
    }

    @Test
    void testCreateAccount() {
        Account acc = bankingService.createAccount("John Doe", 100.0);
        assertNotNull(acc);
        assertEquals(100.0, acc.getBalance());
    }

    @Test
    void testDeposit() {
        Account acc = bankingService.createAccount("John Doe", 100.0);

        bankingService.deposit(acc.getAccountNumber(), 50.0);

        assertEquals(150.0, acc.getBalance());
    }

    @Test
    void testWithdraw() {
        Account acc = bankingService.createAccount("John Doe", 200.0);

        bankingService.withdraw(acc.getAccountNumber(), 50.0);

        assertEquals(150.0, acc.getBalance());
    }
}

