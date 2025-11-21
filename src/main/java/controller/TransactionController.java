package controller;

import model.Transaction;
import service.TransactionService;

import java.util.List;

public class TransactionController {

    private TransactionService transactionService = new TransactionService();

    public void addTransaction(Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    public List<Transaction> getTransactionsForAccount(String accountNumber) {
        return transactionService.getTransactions(accountNumber);
    }
}

