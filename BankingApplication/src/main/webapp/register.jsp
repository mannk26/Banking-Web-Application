<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: #f3f4f6;
        }
        .container {
            background: #ffffff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }
        .container h2 {
            margin-bottom: 20px;
            color: #333333;
        }
        .container label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
            color: #555555;
        }
        .container input, .container select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #cccccc;
            border-radius: 4px;
            font-size: 14px;
        }
        .container input[type="submit"] {
            background: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background 0.3s ease;
        }
        .container input[type="submit"]:hover {
            background: #45a049;
        }
        .container a {
            color: #007BFF;
            text-decoration: none;
            font-size: 14px;
        }
        .container a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>
        <form action="register" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
            
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="customer">Customer</option>
                <option value="admin">Admin</option>
            </select>
            
            <input type="submit" value="Register">
        </form>
        <p><a href="login.jsp">Already have an account? Login here.</a></p>

        <c:if test="${not empty errorMessage}">
            <p class="error-message">${errorMessage}</p>
        </c:if>
    </div>
</body>
</html>
