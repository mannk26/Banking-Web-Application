package com.banking.dao;

import com.banking.model.Account;

import java.util.List;

public interface AccountDao {
    void createAccount(Account account) throws Exception;
    Account getAccount(int accountId) throws Exception; // To get an account by ID
    List<Account> getAccountsByUserId(int userId) throws Exception; // Get accounts by user ID
}
