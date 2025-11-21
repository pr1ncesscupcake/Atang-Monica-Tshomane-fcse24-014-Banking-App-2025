package test.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void testDeposit() {
        Account acc = new Account("12345", 100.0);
        acc.deposit(50);
        assertEquals(150.0, acc.getBalance());
    }

    @Test
    void testWithdraw() {
        Account acc = new Account("12345", 200.0);
        acc.withdraw(50);
        assertEquals(150.0, acc.getBalance());
    }

    @Test
    void testWithdrawInsufficientBalance() {
        Account acc = new Account("12345", 50.0);
        assertThrows(IllegalArgumentException.class, () -> acc.withdraw(100));
    }
}

