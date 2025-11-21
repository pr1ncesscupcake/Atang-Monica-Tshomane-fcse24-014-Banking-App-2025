package model;

public class Transaction {
    private int transactionId;
    private int accountId;
    private double amount;
    private String transactionType;
    private String description;
    private String createdAt;

    public Transaction(int transactionId, int accountId, double amount, String transactionType, String description,
            String createdAt) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Transaction(int accountId, double amount, String transactionType, String description) {
        this(-1, accountId, amount, transactionType, description, null);
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return transactionType;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
