package controller;

import model.Account;
import service.AccountService;

public class AccountController {

    private AccountService accountService = new AccountService();

    // Create new account
    public void createAccount(String accountNumber, int customerId, double initialBalance, String accountType) {
        Account account = new Account(accountNumber, customerId, initialBalance, accountType);
        accountService.createAccount(account);
    }

    // Get account details
    public Account getAccount(String accountNumber) {
        return accountService.getAccount(accountNumber);
    }

    // Deposit funds
    public void deposit(String accountNumber, double amount) {
        accountService.deposit(accountNumber, amount);
    }

    // Withdraw funds
    public void withdraw(String accountNumber, double amount) {
        accountService.withdraw(accountNumber, amount);
    }
}
