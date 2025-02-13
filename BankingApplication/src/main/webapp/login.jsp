<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: 'Poppins', Arial, sans-serif;
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #1e90ff, #6a11cb);
            background-size: cover;
            color: #ffffff;
        }

        .login-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px 30px;
            width: 100%;
            max-width: 400px;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #1e90ff;
            font-size: 28px;
            font-weight: bold;
        }

        .logo {
            margin-bottom: 20px;
        }

        .logo img {
            width: 80px;
        }

        label {
            display: block;
            text-align: left;
            margin-bottom: 8px;
            font-size: 14px;
            font-weight: 600;
            color: #333333;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #cccccc;
            border-radius: 8px;
            font-size: 14px;
            box-sizing: border-box;
            background: #f9f9f9;
        }

        input[type="submit"] {
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

        input[type="submit"]:hover {
            background: linear-gradient(90deg, #6a11cb, #1e90ff);
            transform: scale(1.05);
        }

        .register-link, .forgot-password a {
            display: inline-block;
            margin-top: 15px;
            font-size: 14px;
            color: #1e90ff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .register-link:hover, .forgot-password a:hover {
            color: #6a11cb;
        }

        .forgot-password {
            margin-top: 15px;
        }

        .cta {
            font-size: 14px;
            margin-top: 15px;
            color: #666666;
        }

        @media (max-width: 480px) {
            .login-container {
                padding: 30px 20px;
            }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="logo">
            <img src="logo.png" alt="Logo">
        </div>
        <h2>Welcome Back</h2>
        <form action="login" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>

            <input type="submit" value="Login">
        </form>

        <p class="cta">Don't have an account? <a class="register-link" href="register.jsp">Register now</a></p>
        <div class="forgot-password">
            <a href="forgot_password.jsp">Forgot Password?</a>
        </div>
    </div>
</body>
</html>
