package com.time_tracker;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.time_tracker.dao.AdminDAO;
import com.time_tracker.model.Admin;

@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("admin_name");
        String password = request.getParameter("admin_password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.getAdminByUsernameAndPassword(username, password);

        if (admin != null) {
            session.setAttribute("user_name", admin.getAdminName());
            dispatcher = request.getRequestDispatcher("admin_add_employee.jsp");
        } else {
            request.setAttribute("status", "failed");
            dispatcher = request.getRequestDispatcher("admin_login.jsp");
        }
        dispatcher.forward(request, response);
    }
}
