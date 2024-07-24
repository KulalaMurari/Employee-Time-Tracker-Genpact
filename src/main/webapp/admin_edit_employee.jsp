<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.time_tracker.dao.EmployeeDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
    <link rel="stylesheet" type="text/css" href="CSS/admin_edit_employee.css">
</head>
<body>
<h2>Edit Employee Details</h2>

<!-- Form to search for an employee -->
<form method="get" action="admin_edit_employee.jsp">
    <label>Enter Employee ID:</label>
    <input type="text" name="emp_id" required/>
    <input type="submit" value="Get Details"/>
</form>

<%
String emp_id = request.getParameter("emp_id");
ResultSet rs = null;
try {
    if (emp_id != null && !emp_id.isEmpty()) {
        // DAO method to get employee details
        rs = EmployeeDAO.getEmployeeById(emp_id);
        if (rs != null && rs.next()) {
%>
        <!-- Display employee details -->
        <table>
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Role</th>
                <th>Phone Number</th>
                <th>Email</th>
            </tr>
            <tr>
                <td><%= rs.getString("emp_id") %></td>
                <td><%= rs.getString("emp_name") %></td>
                <td><%= rs.getString("age") %></td>
                <td><%= rs.getString("role") %></td>
                <td><%= rs.getString("phone_number") %></td>
                <td><%= rs.getString("email") %></td>
            </tr>
        </table><br><br>

        <!-- Form to update employee details -->
        <form action="AdminUpdateEmployeeServlet" method="post">
            <input type="hidden" name="emp_id" value="<%= rs.getString("emp_id") %>"/>
            <label>Name</label>
            <input type="text" name="new_name" value="<%= rs.getString("emp_name") %>" required/><br><br>
            <label>Age</label>
            <input type="text" name="new_age" value="<%= rs.getString("age") %>" required/><br><br>
            <label>Role</label>
            <input type="text" name="new_role" value="<%= rs.getString("role") %>" required/><br><br>
            <label>Phone Number</label>
            <input type="text" name="new_phone" value="<%= rs.getString("phone_number") %>" required/><br><br>
            <label>Email</label>
            <input type="email" name="new_email" value="<%= rs.getString("email") %>" required/><br><br>
            <input type="submit" value="Update">
        </form>
<%
        } else {
            out.println("<p>No employee found with the provided ID.</p>");
        }
    }
} catch (Exception e) {
    e.printStackTrace();
    out.println("<p>Error occurred: " + e.getMessage() + "</p>");
} finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
%>
</body>
</html>
