<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); // Redirect to login if session is invalid
        return; // Prevent further execution
    }
    String greeting;
    int hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY);
    if (hour < 12) {
        greeting = "Good Morning";
    } else if (hour < 18) {
        greeting = "Good Afternoon";
    } else {
        greeting = "Good Evening";
    }
%>
<h1><%= greeting %>, <%= user.getUsername() %>!</h1>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            background-color: #f4f6f9;
            color: #333;
            line-height: 1.6;
        }
        .header {
            background: linear-gradient(90deg, #007BFF, #0056b3);
            color: #fff;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        }
        .header h1 {
            margin: 0;
            font-size: 2.5em;
            letter-spacing: 1px;
        }
        .dashboard {
            background-color: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            max-width: 900px;
            margin: 30px auto;
            transition: transform 0.3s ease;
        }
        .dashboard:hover {
            transform: scale(1.02);
        }
        .dashboard h2 {
            margin-top: 0;
        }
        .account-info {
            background-color: #e9ecef;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 25px;
            border-left: 4px solid #007BFF;
        }
        .nav {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
        }
        .nav a {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #007BFF;
            color: #fff;
            padding: 20px;
            border-radius: 8px;
            text-decoration: none;
            font-size: 1.1em;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s, transform 0.3s;
        }
        .nav a:hover {
            background-color: #0056b3;
            transform: translateY(-5px);
        }
        .nav a i {
            margin-right: 10px;
            font-size: 1.2em;
        }
        .footer {
            margin-top: 40px;
            padding: 20px;
            text-align: center;
            font-size: 0.9em;
            color: #666;
            background-color: #f9f9f9;
            border-top: 1px solid #ddd;
        }
        .footer a {
            color: #007BFF;
            text-decoration: none;
            margin: 0 10px;
        }
        .footer a:hover {
            text-decoration: underline;
        }
        .cta {
            text-align: center;
            margin-top: 30px;
        }
        .cta a {
            background-color: #28a745;
            color: #fff;
            padding: 15px 25px;
            font-size: 1.2em;
            text-decoration: none;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }
        .cta a:hover {
            background-color: #218838;
        }
        .logout {
            color: #dc3545;
            text-decoration: none;
            font-size: 1.2em;
        }
        .logout:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Welcome to Your Dashboard, <%= user.getUsername() %>!</h1>
    </div>

    <div class="dashboard">
<div class="account-info">
    <h2>Account Details</h2>
    <% 
        // Assuming user.getId() returns an int, convert it to a String
        String accountId = String.valueOf(user.getId());
        String displayAccountId = accountId.length() > 4 ? "****" + accountId.substring(accountId.length() - 4) : accountId;
    %>
    <p><strong>Account Number:</strong> <%= displayAccountId %></p>
    <p><strong>Current Balance:</strong> ₹ <%= user.getAccountBalance() %></p>
</div>


        <p><strong>Choose an action:</strong></p>
        <div class="nav">
            <a href="addWithdraw.jsp"><i class="fas fa-wallet"></i>Add/Withdraw Balance</a>
            <a href="transferMoney.jsp"><i class="fas fa-exchange-alt"></i>Transfer Money</a>
            <a href="registerComplaint.jsp"><i class="fas fa-comments"></i>Register Complaint</a>
            <a href="transactionHistory.jsp"><i class="fas fa-history"></i>Transaction History</a>
            <a href="updateProfile.jsp"><i class="fas fa-user-edit"></i>Update Profile</a>
            <a href="investmentOptions.jsp"><i class="fas fa-chart-line"></i>Investment Options</a>
        </div>

        <div class="cta">
            <a href="financialAdvice.jsp"><i class="fas fa-lightbulb"></i> Get Financial Advice</a>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <form action="logout" method="get" style="display:inline;">
                <button type="submit" class="logout"><i class="fas fa-sign-out-alt"></i> Logout</button>
            </form>
        </div>
    </div>
    
    <div class="footer">
        <p>&copy; 2024 Online Banking. All rights reserved.</p>
        <p><a href="support.jsp">Support</a> | <a href="contact.jsp">Contact Us</a> | <a href="terms.jsp">Terms & Conditions</a></p>
    </div>
</body>
</html>
