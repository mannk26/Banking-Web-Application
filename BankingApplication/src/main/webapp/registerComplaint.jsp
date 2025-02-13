<!DOCTYPE html>
<html>
<head>
    <title>Register Complaint</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 50px auto;
            padding: 20px;
            background: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        .form-group textarea {
            height: 100px;
        }
        .btn {
            display: inline-block;
            background-color: #007bff;
            color: #ffffff;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .link-container {
            text-align: right;
            margin-top: 20px;
        }
        .link-container a {
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }
        .link-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Register Complaint</h1>
        <form action="registerComplaint" method="POST">
            <div class="form-group">
                <label for="subject">Subject:</label>
                <input type="text" id="subject" name="subject" placeholder="Enter complaint subject" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" placeholder="Describe your complaint" required></textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="btn">Submit Complaint</button>
            </div>
        </form>
        <div class="link-container">
            <a href="complaintHistory.jsp">View Your Previous Complaints</a>
        </div>
    </div>
</body>
</html>
