package com.time_tracker;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time_tracker.dao.EmployeeDAO;

@WebServlet("/admin_update_employee")
public class AdminUpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("emp_id");
        String newName = request.getParameter("new_name");
        String newAge = request.getParameter("new_age");
        String newRole = request.getParameter("new_role");
        String newPhone = request.getParameter("new_phone");
        String newEmail = request.getParameter("new_email");

        if (empId == null || empId.isEmpty()) {
            request.setAttribute("message", "Employee ID is required.");
            request.getRequestDispatcher("admin_edit_employee.jsp").forward(request, response);
            return;
        }

        boolean updated = EmployeeDAO.updateEmployee(empId, newName, newAge, newRole, newPhone, newEmail);

        if (updated) {
            response.sendRedirect("admin_edit_employee.jsp");
        } else {
            request.setAttribute("message", "Failed to update employee details.");
            request.getRequestDispatcher("admin_edit_employee.jsp").forward(request, response);
        }
    }
}
