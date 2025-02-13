package com.banking.controller;

import com.banking.dao.UserDao;
import com.banking.dao.UserDaoImpl;
import com.banking.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl(); // Initialize UserDao

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // Create a new User object
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Hash the password
        user.setEmail(email);
        user.setRole(role);
        user.setAccountBalance(0.0);

        // Insert the user into the database
        try {
            boolean createduser = userDao.createUser(user);
            if(createduser) {
                response.sendRedirect("login.jsp"); // Redirect to login page after successful registration
            }else {
            	System.out.println(createduser);
            }
        } catch (Exception e) {
            // Handle exceptions such as duplicate username
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
