<%@ page import="java.util.List" %>
<%@ page import="com.banking.model.Complaint" %>

<!DOCTYPE html>
<html>
<head>
    <title>Complaint History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Complaint History</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Subject</th>
                <th>Description</th>
                <th>Status</th>
                <th>Resolution</th>
                <th>Created At</th>
                <th>Updated At</th>
            </tr>
            <%
                List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
                if (complaints != null && !complaints.isEmpty()) {
                    for (Complaint complaint : complaints) {
            %>
            <tr>
                <td><%= complaint.getId() %></td>
                <td><%= complaint.getSubject() %></td>
                <td><%= complaint.getDescription() %></td>
                <td><%= complaint.getStatus() %></td>
                <td><%= complaint.getResolution() == null ? "N/A" : complaint.getResolution() %></td>
                <td><%= complaint.getCreatedAt() %></td>
                <td><%= complaint.getUpdatedAt() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">No complaints found</td>
            </tr>
            <%
                }
            %>
        </table>
        <br>
        <a href="registerComplaint.jsp" class="btn">Register New Complaint</a>
    </div>
</body>
</html>
