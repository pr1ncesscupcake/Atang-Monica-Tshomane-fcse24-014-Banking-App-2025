package model;

import java.text.DecimalFormat;

/**
 * Abstract base class for all bank accounts.
 * Implements common functionality and defines the abstract contract for interest calculation.
 */
public abstract class Account {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    protected final String accountNumber;
    protected double balance;
    protected final String branch;
    protected final Customer customer;

    public Account(String accountNumber, Customer customer, String branch, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.branch = branch;
        this.balance = initialDeposit;
    }

    public Account(String accountNumber, Customer customer, String branch) {
        this(accountNumber, customer, branch, 0.00);
    }

    /**
     * Abstract method to be implemented by subclasses for specific interest logic.
     */
    public abstract void calculateInterest();

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getBranch() {
        return branch;
    }

    // Setter for persistence logic (used by DAO to restore state)
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("%s [Number: %s, Balance: $%s, Customer: %s]",
                this.getClass().getSimpleName(), accountNumber, df.format(balance), customer.getFirstName());
    }
}
