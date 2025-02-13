package com.banking.controller;

import com.banking.dao.TransactionDao;

import com.banking.dao.TransactionDaoImpl;
import com.banking.dao.UserDao;
import com.banking.dao.UserDaoImpl;
import com.banking.model.Transaction;
import com.banking.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Ensure this is hashed
        TransactionDao transactionService = new TransactionDaoImpl();

        User user = userDao.getUserByUsername(username);
        List<Transaction> transactions = transactionService.getTransactionsByUserId(user.getId());

        if (user != null && user.getPassword().equals(password)) { // Add password hashing check
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("transactions", transactions);
            response.sendRedirect("dashboard.jsp"); // Redirect to user dashboard after login
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
