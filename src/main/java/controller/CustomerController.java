package controller;

import dao.AccountDAO;
import dao.CustomerDAO;
import dao.TransactionDAO;
import model.Account;
import model.Customer;
import model.Transaction;

import java.util.List;

public class CustomerController {
    private final AccountDAO accountDAO = new AccountDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    public Account getAccountByNumber(String accountNumber) {
        return accountDAO.getAccountByNumber(accountNumber);
    }

    public boolean deposit(String accountNumber, double amount) {
        Account acc = accountDAO.getAccountByNumber(accountNumber);
        if (acc == null)
            return false;
        try {
            acc.deposit(amount);
        } catch (IllegalArgumentException e) {
            return false;
        }
        boolean ok = accountDAO.updateBalance(accountNumber, acc.getBalance());
        if (!ok)
            return false;
        Transaction tx = new Transaction(acc.getAccountId(), amount, "DEPOSIT", "Deposit");
        return transactionDAO.addTransaction(tx);
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account acc = accountDAO.getAccountByNumber(accountNumber);
        if (acc == null)
            return false;
        try {
            acc.withdraw(amount);
        } catch (IllegalArgumentException e) {
            return false;
        }
        boolean ok = accountDAO.updateBalance(accountNumber, acc.getBalance());
        if (!ok)
            return false;
        Transaction tx = new Transaction(acc.getAccountId(), amount, "WITHDRAW", "Withdrawal");
        return transactionDAO.addTransaction(tx);
    }

    public List<Transaction> getTransactionsForAccount(int accountId) {
        return transactionDAO.getTransactionsByAccount(accountId);
    }

    public void createCustomer(String name, String email, String phone) {
        Customer c = new Customer(0, name, email, phone);
        customerDAO.createCustomer(c);
    }
}
