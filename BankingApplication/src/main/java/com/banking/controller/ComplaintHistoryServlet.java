package com.banking.controller;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.banking.model.*;
import com.banking.dao.*;

import com.banking.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/complaintHistory")
public class ComplaintHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("user_id");

        List<Complaint> complaints = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM complaints WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Complaint complaint = new Complaint();
               
                complaint.setId(rs.getInt("complaint_id"));
                complaint.setUserId(rs.getString("user_id"));
                complaint.setSubject(rs.getString("subject"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setResolution(rs.getString("resolution"));
                complaint.setCreatedAt(rs.getTimestamp("created_at"));
                complaint.setUpdatedAt(rs.getTimestamp("updated_at"));
                complaints.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("complaintHistory.jsp").forward(request, response);
    }
}

