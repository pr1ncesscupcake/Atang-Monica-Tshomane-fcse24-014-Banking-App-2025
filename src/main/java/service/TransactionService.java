package service;

import dao.AccountDAO;
import dao.TransactionDAO;
import model.Account;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAO();

    public void addTransaction(Transaction t) {
        transactionDAO.addTransaction(t);
    }

    public List<Transaction> getTransactions(String accountNumber) {
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account == null)
            return new ArrayList<>();
        return transactionDAO.getTransactionsByAccount(account.getAccountId());
    }
}
