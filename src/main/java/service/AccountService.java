package service;

import dao.AccountDAO;
import dao.TransactionDAO;
import model.Account;
import model.Transaction;

import java.util.List;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    // Create account
    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }

    // Get account details
    public Account getAccount(String accountNumber) {
        return accountDAO.getAccountByNumber(accountNumber);
    }

    // Get all accounts
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    // Deposit business logic
    public void deposit(String accountNumber, double amount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        account.deposit(amount);
        accountDAO.updateBalance(accountNumber, account.getBalance());

        Transaction t = new Transaction(
                account.getAccountId(),
                amount,
                "DEPOSIT",
                "Deposit");
        transactionDAO.addTransaction(t);
    }

    // Withdraw business logic
    public void withdraw(String accountNumber, double amount) {
        Account account = accountDAO.getAccountByNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        account.withdraw(amount); // model logic
        accountDAO.updateBalance(accountNumber, account.getBalance());

        Transaction t = new Transaction(
                account.getAccountId(),
                amount,
                "WITHDRAW",
                "Withdrawal");
        transactionDAO.addTransaction(t);
    }
}
