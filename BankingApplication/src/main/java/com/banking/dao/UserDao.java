package com.banking.dao;

import com.banking.model.User;

public interface UserDao {
    boolean createUser(User user);
    User getUserByUsername(String username);
	boolean updateUserBalance(User user);
	boolean transferMoney(User sender, String receiverAccount, double amount);
}
