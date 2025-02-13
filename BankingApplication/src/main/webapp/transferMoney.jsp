<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.banking.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Money</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: auto;
        }
        h2 {
            color: #333;
        }
        label {
            font-weight: bold;
        }
        input, button {
            display: block;
            margin: 10px 0;
            padding: 8px;
            width: 100%;
        }
        button {
            background-color: #28a745;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Transfer Money</h2>
        <form action="TransferMoneyServlet" method="post">
            <label for="receiverAccount">Receiver Account Number:</label>
            <input type="text" name="receiverAccount" id="receiverAccount" required>

            <label for="amount">Enter Amount:</label>
            <input type="number" name="amount" id="amount" min="1" required>

            <button type="submit">Transfer</button>
        </form>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
