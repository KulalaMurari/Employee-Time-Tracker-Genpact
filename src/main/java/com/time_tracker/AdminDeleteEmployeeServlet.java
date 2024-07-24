package com.time_tracker;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.time_tracker.dao.EmployeeDAO;

@WebServlet("/admin_delete_employee")
public class AdminDeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("emp_id");
        EmployeeDAO employeeDAO = new EmployeeDAO();

        try {
            boolean isDeleted = employeeDAO.deleteEmployee(empId);
            if (isDeleted) {
                response.getWriter().write("Employee record deleted successfully.");
            } else {
                response.getWriter().write("Failed to delete employee record.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error occurred: " + e.getMessage());
        }
    }
}
