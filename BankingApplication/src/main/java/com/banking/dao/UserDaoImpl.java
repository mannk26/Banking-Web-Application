package com.banking.dao;

import com.banking.model.User;
import com.banking.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean createUser(User user) {
    	boolean isCreated = true;
        String sql = "INSERT INTO User (username, password, email, role, userbalance ) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // Ensure this is hashed
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setDouble(4, user.getAccountBalance());
            
            
            // Execute the update query
            int rowsAffected = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                isCreated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCreated;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM User WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                user.setAccountBalance(rs.getDouble("userbalance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUserBalance(User user) {
    	String sql = "UPDATE user SET userbalance = ? WHERE id = ?";
        
        boolean isUpdated = false;

        try {
            // Prepare the SQL statement
        	Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, user.getAccountBalance()); // Set userbalance value
            stmt.setInt(2, user.getId());       // Set user ID condition

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        return isUpdated;
    }

    public boolean transferMoney(User sender, String receiverAccount, double amount) {
        Connection connection = null;
        PreparedStatement checkReceiverStmt = null;
        PreparedStatement updateSenderStmt = null;
        PreparedStatement updateReceiverStmt = null;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Check if receiver exists
            checkReceiverStmt = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            checkReceiverStmt.setString(1, receiverAccount);
            ResultSet rs = checkReceiverStmt.executeQuery();

            if (!rs.next()) {
                connection.rollback();
                return false; // Receiver doesn't exist
            }

            int receiverId = rs.getInt("id");
            double receiverBalance = rs.getDouble("userbalance");

            // Check sender balance
            if (sender.getAccountBalance() < amount) {
                connection.rollback();
                return false; // Insufficient balance
            }

            // Deduct amount from sender
            double newSenderBalance = sender.getAccountBalance() - amount;
            updateSenderStmt = connection.prepareStatement("UPDATE user SET userbalance = ? WHERE id = ?");
            updateSenderStmt.setDouble(1, newSenderBalance);
            updateSenderStmt.setInt(2, sender.getId());
            updateSenderStmt.executeUpdate();

            // Add amount to receiver
            double newReceiverBalance = receiverBalance + amount;
            updateReceiverStmt = connection.prepareStatement("UPDATE user SET userbalance = ? WHERE id = ?");
            updateReceiverStmt.setDouble(1, newReceiverBalance);
            updateReceiverStmt.setInt(2, receiverId);
            updateReceiverStmt.executeUpdate();

            // Commit transaction
            connection.commit();

            // Update sender's balance in session
            sender.setAccountBalance(newSenderBalance);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        return false;
    }




}
