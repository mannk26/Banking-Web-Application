package com.banking.controller;

import com.banking.model.Transaction;
import com.banking.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.banking.dao.*;


import java.io.IOException;
import java.util.List;

@WebServlet("/TransactionHistoryServlet")
public class TransactionHistoryServlet extends HttpServlet {

    private TransactionDao transactionService = new TransactionDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("TransactionHistoryServlet called here");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch transaction history
        List<Transaction> transactions = transactionService.getTransactionsByUserId(user.getId());

        // Set transactions in request scope
        session.setAttribute("user", user);
        session.setAttribute("transactions", transactions);
        request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
    }
}
