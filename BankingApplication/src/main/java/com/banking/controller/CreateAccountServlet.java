package com.banking.controller;

import com.banking.dao.AccountDao;
import com.banking.dao.AccountDaoImpl;
import com.banking.model.Account;
import com.banking.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
    private AccountDao accountDao;

    @Override
    public void init() {
        accountDao = new AccountDaoImpl(); // Initialize AccountDao
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountType = request.getParameter("accountType");
        String initialBalanceParam = request.getParameter("initialBalance");

        // Validate input
        if (accountType == null || accountType.isEmpty() || initialBalanceParam == null || initialBalanceParam.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("create_account.jsp").forward(request, response);
            return;
        }

        double initialBalance;
        try {
            initialBalance = Double.parseDouble(initialBalanceParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid balance amount.");
            request.getRequestDispatcher("create_account.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId(); // Get user ID from the session

        Account account = new Account();
        account.setUserId(userId);
        account.setAccountType(accountType);
        account.setBalance(initialBalance);

        try {
            accountDao.createAccount(account); // Create the account
            request.getSession().setAttribute("successMessage", "Account created successfully!"); // Success message
            response.sendRedirect("dashboard.jsp"); // Redirect to dashboard after account creation
        } catch (Exception e) {
            e.printStackTrace(); // Consider logging instead
            request.setAttribute("errorMessage", "Account creation failed. Please try again.");
            request.getRequestDispatcher("create_account.jsp").forward(request, response);
        }
    }
}
