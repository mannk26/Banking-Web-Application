package com.banking.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.banking.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registerComplaint")
public class RegisterComplaintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("user_id");
        String subject = request.getParameter("subject");
        String description = request.getParameter("description");

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO complaints (user_id, subject, description, status, created_at) VALUES (?, ?, ?, 'Pending', NOW())";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, subject);
            stmt.setString(3, description);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                response.sendRedirect("complaintHistory.jsp?message=Complaint registered successfully");
            } else {
                response.sendRedirect("registerComplaint.jsp?error=Failed to register complaint");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("registerComplaint.jsp?error=An error occurred");
        }
    }
}
