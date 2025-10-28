package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents the Bank entity, managing account creation and system-wide operations.
 */
public class Bank {
    private final String bankName;
    // Simple in-memory list to track open accounts for business logic simulation (like interest)
    private final List<Account> openAccounts = new ArrayList<>();
    // Simple account number generator
    private static final AtomicInteger ACCOUNT_COUNTER = new AtomicInteger(100000);

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    private String getNextAccountNumber() {
        return "ACC" + ACCOUNT_COUNTER.getAndIncrement();
    }

    // Factory method for SavingsAccount
    public SavingsAccount openAccount(Customer customer, String branch) {
        String newAccNum = getNextAccountNumber();
        SavingsAccount newAccount = new SavingsAccount(newAccNum, customer, branch, 0.00);
        openAccounts.add(newAccount);
        return newAccount;
    }

    // Factory method for ChequeAccount
    public ChequeAccount openAccount(Customer customer, String branch, String employerName, String employerAddress) {
        String newAccNum = getNextAccountNumber();
        ChequeAccount newAccount = new ChequeAccount(newAccNum, customer, branch, employerName, employerAddress);
        openAccounts.add(newAccount);
        return newAccount;
    }

    // Factory method for InvestmentAccount
    public InvestmentAccount openAccount(Customer customer, String branch, double initialDeposit) {
        String newAccNum = getNextAccountNumber();
        InvestmentAccount newAccount = new InvestmentAccount(newAccNum, customer, branch, initialDeposit);
        openAccounts.add(newAccount);
        return newAccount;
    }

    /**
     * Executes the polymorphic interest calculation across all bank accounts.
     */
    public void calculateMonthlyInterest() {
        System.out.println("BUSINESS: Calculating monthly interest for all accounts...");
        for (Account account : openAccounts) {
            // Polymorphic call: the specific calculateInterest method is called based on the object's runtime type
            account.calculateInterest();
        }
    }

    // Getters
    public String getBankName() {
        return bankName;
    }
}