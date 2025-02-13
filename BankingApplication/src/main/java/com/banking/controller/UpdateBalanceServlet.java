package com.banking.controller;

import com.banking.model.Transaction;
import com.banking.model.User;
import com.banking.dao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
@WebServlet("/UpdateBalanceServlet")
public class UpdateBalanceServlet extends HttpServlet {

    private UserDao userService = new UserDaoImpl();
    private TransactionDao transactionService = new TransactionDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String transactionType = request.getParameter("transactionType");
        double amount;

        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid amount entered!");
            request.getRequestDispatcher("addWithdraw.jsp").forward(request, response);
            return;
        }

        // Validation
        if (amount <= 0) {
            request.setAttribute("error", "Amount must be greater than zero!");
            request.getRequestDispatcher("addWithdraw.jsp").forward(request, response);
            return;
        }

        double currentBalance = user.getAccountBalance();

        if ("add".equalsIgnoreCase(transactionType)) {
            currentBalance += amount;
        } else if ("withdraw".equalsIgnoreCase(transactionType)) {
            if (currentBalance < amount) {
                request.setAttribute("error", "Insufficient balance!");
                request.getRequestDispatcher("addWithdraw.jsp").forward(request, response);
                return;
            }
            currentBalance -= amount;
        }

        // Update balance in the database
        user.setAccountBalance(currentBalance);
        boolean updation = userService.updateUserBalance(user);

        if (updation) {
            // Create transaction record
            Transaction transaction = new Transaction();
            System.out.println(user.getId()+" "+ user.getUsername());
            transaction.setUserId(user.getId());
            transaction.setReceiverAccount("SELF"); // Use "SELF" as the receiver for balance updates
            transaction.setAmount(amount);
            transaction.setTransactionType(transactionType.equalsIgnoreCase("add") ? "CREDIT" : "DEBIT");

            boolean trans = transactionService.createTransaction(transaction);
            System.out.println("transaction created: "+ trans);
            // Update session
            session.setAttribute("user", user);
            //session.setAttribute("transaction", transaction);

            List<Transaction> transactions = transactionService.getTransactionsByUserId(user.getId());
            
            // Success message and redirect
            session.setAttribute("transactions", transactions);
            request.setAttribute("success", "Transaction successful! New Balance: ₹" + currentBalance);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            

        } else {
            request.setAttribute("error", "Failed to update balance. Please try again!");
            request.getRequestDispatcher("addWithdraw.jsp").forward(request, response);
        }
    }
}

