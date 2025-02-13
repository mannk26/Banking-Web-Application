<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.banking.model.Transaction" %>
<%
	
    User user = (User) session.getAttribute("user");
	//Transaction transaction = (Transaction) session.getAttribute("transaction");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    List<Transaction> transactions = (List<Transaction>) session.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
        .back-link a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <h2>Transaction History</h2>
    <table>
        <tr>
            <th>Transaction ID</th>
            <th>Date</th>
            <th>Amount</th>
            <th>Type</th>
            <th>Receiver Account</th>
        </tr>
        <% if (transactions != null && !transactions.isEmpty()) { %>
            <% for (Transaction transaction : transactions) { %>
                <tr>
                    <td><%= transaction.getId() %></td>
                    <td><%= transaction.getTransactionDate() %></td>
                    <td><%= transaction.getAmount() %></td>
                    <td><%= transaction.getTransactionType() %></td>
                    <td><%= transaction.getReceiverAccount() != null ? transaction.getReceiverAccount() : "-" %></td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="5">No transactions found.</td>
            </tr>
        <% } %>
    </table>

    <div class="back-link">
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
