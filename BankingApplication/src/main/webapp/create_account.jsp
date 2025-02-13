<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
</head>
<body>
    <h2>Create New Account</h2>
    <form action="createAccount" method="post">
        <label for="accountType">Account Type:</label>
        <select id="accountType" name="accountType" required>
            <option value="Savings">Savings</option>
            <option value="Current">Current</option>
            <option value="Fixed Deposit">Fixed Deposit</option>
        </select><br><br>

        <label for="initialBalance">Initial Balance:</label>
        <input type="number" id="initialBalance" name="initialBalance" step="0.01" required><br><br>
        
        <input type="submit" value="Create Account">
    </form>
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
