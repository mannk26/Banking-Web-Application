package com.banking.dao;

import java.util.List;

import com.banking.model.Transaction;

public interface TransactionDao {
    boolean createTransaction(Transaction transaction);
    Transaction getTransactionById(int transactionId);
    List<Transaction> getTransactionsByUserId(int userId);
    List<Transaction> getAllTransactions();
}
