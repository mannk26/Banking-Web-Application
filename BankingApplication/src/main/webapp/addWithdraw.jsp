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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add/Withdraw Balance</title>
    <style>
        body {
            font-family: 'Poppins', Arial, sans-serif;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #1e90ff, #6a11cb);
            color: #ffffff;
        }

        .form-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 30px 25px;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
            text-align: center;
            width: 100%;
            max-width: 400px;
        }

        h2 {
            color: #1e90ff;
            font-size: 26px;
            margin-bottom: 20px;
        }

        label {
            display: block;
            text-align: left;
            margin-bottom: 8px;
            font-size: 14px;
            font-weight: bold;
            color: #333333;
        }

        select, input[type="number"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #cccccc;
            border-radius: 8px;
            font-size: 14px;
            box-sizing: border-box;
            background: #f9f9f9;
        }

        button {
            background: linear-gradient(90deg, #1e90ff, #6a11cb);
            color: #ffffff;
            border: none;
            padding: 12px;
            width: 100%;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease-in-out;
        }

        button:hover {
            background: linear-gradient(90deg, #6a11cb, #1e90ff);
            transform: scale(1.05);
        }

        a {
            display: inline-block;
            margin-top: 15px;
            font-size: 14px;
            color: #1e90ff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #6a11cb;
        }

        @media (max-width: 480px) {
            .form-container {
                padding: 20px 15px;
            }
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Add or Withdraw Balance</h2>
        <form action="UpdateBalanceServlet" method="post">
            <label for="transactionType">Choose Transaction:</label>
            <select name="transactionType" id="transactionType" required>
                <option value="add">Add Balance</option>
                <option value="withdraw">Withdraw Balance</option>
            </select>

            <label for="amount">Enter Amount:</label>
            <input type="number" name="amount" id="amount" min="1" placeholder="Enter amount in ₹" required>

            <button type="submit">Submit</button>
        </form>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
