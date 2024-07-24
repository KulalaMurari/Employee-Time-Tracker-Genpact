package com.time_tracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.time_tracker.DBConnection;

@WebServlet("/add-task")
public class AddTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String employeeId = (String) session.getAttribute("emp_id");
        String employeeName = (String) session.getAttribute("emp_name");
        int taskId = generateTaskId();
        String projectName = request.getParameter("project-name");
        String role = request.getParameter("task-role");
        String taskDate = request.getParameter("task-date");
        String startTime = request.getParameter("task-start-time");
        String endTime = request.getParameter("task-end-time");
        String taskCategory = request.getParameter("task-category");
        String taskDescription = request.getParameter("task-description");

        // Calculate duration
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long durationInSeconds = 0;

        try {
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            durationInSeconds = (end.getTime() - start.getTime()) / 1000; // Duration in seconds
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            request.setAttribute("message", "Time parsing error.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Insert task into the database
        String query = "INSERT INTO task_table (emp_id, emp_name, task_id, project_name, task_role, task_date, start_time, end_time, duration, task_category, task_description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, employeeId);
            ps.setString(2, employeeName);
            ps.setInt(3, taskId);
            ps.setString(4, projectName);
            ps.setString(5, role);
            ps.setString(6, taskDate);
            ps.setString(7, startTime);
            ps.setString(8, endTime);
            ps.setLong(9, durationInSeconds); // Set duration in seconds
            ps.setString(10, taskCategory);
            ps.setString(11, taskDescription);

            int rowcount = ps.executeUpdate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            if (rowcount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            request.setAttribute("message", "Database error.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        }
    }

    private int generateTaskId() {
        return new Random().nextInt(99999999);
    }
}
