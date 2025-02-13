package com.banking.dao;

import com.banking.model.Account;
import com.banking.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void createAccount(Account account) throws Exception {
        String sql = "INSERT INTO accounts (user_id, account_type, balance) VALUES (?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, account.getUserId());
            statement.setString(2, account.getAccountType());
            statement.setDouble(3, account.getBalance());
            statement.executeUpdate();
            
            // Retrieve generated account ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                account.setAccountId(generatedKeys.getInt(1)); // Set generated account ID
            }
        }
    }

    @Override
    public Account getAccount(int accountId) throws Exception {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        Account account = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getInt("account_id")); // Set account ID
                account.setUserId(rs.getInt("user_id"));
                account.setAccountType(rs.getString("account_type"));
                account.setBalance(rs.getDouble("balance"));
            }
        }
        return account;
    }

    @Override
    public List<Account> getAccountsByUserId(int userId) throws Exception {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id")); // Set account ID
                account.setUserId(rs.getInt("user_id"));
                account.setAccountType(rs.getString("account_type"));
                account.setBalance(rs.getDouble("balance"));
                accounts.add(account);
            }
        }
        return accounts;
    }
}
