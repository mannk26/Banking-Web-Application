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

@WebServlet("/TransferMoneyServlet")
public class TransferMoneyServlet extends HttpServlet {

    private UserDao userService = new UserDaoImpl();
    private TransactionDao transactionService = new TransactionDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User sender = (User) session.getAttribute("user");

        if (sender == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String receiverAccount = request.getParameter("receiverAccount");
        double amount;
        request.setAttribute("receiverAccount", receiverAccount);
        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid amount entered!");
            request.getRequestDispatcher("transferMoney.jsp").forward(request, response);
            return;
        }

        // Validation
        if (amount <= 0) {
            request.setAttribute("error", "Amount must be greater than 0.");
            request.getRequestDispatcher("transferMoney.jsp").forward(request, response);
            return;
        }

        if (sender.getAccountBalance() < amount) {
            request.setAttribute("error", "Insufficient balance!");
            request.getRequestDispatcher("transferMoney.jsp").forward(request, response);
            return;
        }

        // Transfer Money Logic
        boolean success = userService.transferMoney(sender, receiverAccount, amount);

        if (success) {
            // Deduct amount from sender's balance and update session
            sender.setAccountBalance(sender.getAccountBalance() - amount);
            session.setAttribute("user", sender);

            // Create transaction for sender (DEBIT)
            Transaction senderTransaction = new Transaction();
            senderTransaction.setUserId(sender.getId());
            senderTransaction.setReceiverAccount(receiverAccount);
            senderTransaction.setAmount(amount);
            senderTransaction.setTransactionType("DEBIT");
            transactionService.createTransaction(senderTransaction);

            // Create transaction for receiver (CREDIT)
            User receiver = userService.getUserByUsername(receiverAccount);
            if (receiver != null) {
                Transaction receiverTransaction = new Transaction();
                receiverTransaction.setUserId(receiver.getId());
                receiverTransaction.setReceiverAccount(String.valueOf(sender.getId()));
                receiverTransaction.setAmount(amount);
                receiverTransaction.setTransactionType("CREDIT");
                transactionService.createTransaction(receiverTransaction);
            }

            List<Transaction> transactions = transactionService.getTransactionsByUserId(sender.getId());
            
            session.setAttribute("transactions", transactions);
            // Success message
            request.setAttribute("success", "Money transferred successfully!");
        } else {
            request.setAttribute("error", "Transfer failed! Check account details or balance.");
        }

        request.getRequestDispatcher("transferMoney.jsp").forward(request, response);
    }
}
