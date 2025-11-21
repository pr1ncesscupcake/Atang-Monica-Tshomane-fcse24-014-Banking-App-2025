package model;

public class Account {
    private int accountId;
    private String accountNumber;
    private int customerId;
    private double balance;
    private String accountType;
    private String createdAt;

    public Account(int accountId, String accountNumber, int customerId, double balance, String accountType,
            String createdAt) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
        this.createdAt = createdAt;
    }

    public Account(String accountNumber, int customerId, double balance, String accountType) {
        this(-1, accountNumber, customerId, balance, accountType, null);
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // business logic
    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        if (amount > this.balance)
            throw new IllegalArgumentException("Insufficient funds");
        this.balance -= amount;
    }
}
