package com.banking.dao;

import com.banking.model.Transaction;
import com.banking.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public boolean createTransaction(Transaction transaction) {
        
    	String query = "INSERT INTO transactions (user_id, receiver_account, amount, transaction_type, transaction_date) "
                     + "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, transaction.getUserId());
            statement.setString(2, transaction.getReceiverAccount());
            statement.setDouble(3, transaction.getAmount());
            statement.setString(4, transaction.getTransactionType());

            if(statement.executeUpdate()>0) {
            	return true;
            }else {
            	return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating transaction", e);
            
        }
        
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        String query = "SELECT * FROM transactions WHERE id = ?";
        Transaction transaction = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, transactionId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setReceiverAccount(rs.getString("receiver_account"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE user_id = ? ORDER BY transaction_date DESC";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setReceiverAccount(rs.getString("receiver_account"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transactions ORDER BY transaction_date DESC";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setReceiverAccount(rs.getString("receiver_account"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
