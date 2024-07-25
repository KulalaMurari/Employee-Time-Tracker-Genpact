package com.time_tracker;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time_tracker.dao.EmployeeDAO;
import com.time_tracker.model.Employee;

@WebServlet("/admin_add_emp")
public class AdminAddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emp_name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String role = request.getParameter("employee_role");
        String phone_number = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int emp_id = generateEmployeeId();
        String emp_password = generateEmployeePassword();

        Employee employee = new Employee(emp_id, emp_name, age, role, phone_number, email, password, emp_password);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        boolean isAdded = employeeDAO.addEmployee(employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_add_employee.jsp");
        if (isAdded) {
            request.setAttribute("status", "success");
        } else {
            request.setAttribute("status", "failed");
        }
        dispatcher.forward(request, response);
    }

    private int generateEmployeeId() {
        return new Random().nextInt(99999999); 
    }

    private String generateEmployeePassword() {
        return String.valueOf(new Random().nextInt(999999));
    }
}
