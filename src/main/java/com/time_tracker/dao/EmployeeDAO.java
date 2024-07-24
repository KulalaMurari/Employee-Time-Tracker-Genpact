package com.time_tracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.time_tracker.model.Employee;
import com.util.time_tracker.DBConnection;

public class EmployeeDAO {
    
    private static final String INSERT_QUERY = "INSERT INTO employee_table (emp_id, emp_name, age, role, phone_number, email, password, emp_password) VALUES(?,?,?,?,?,?,?,?)";

    public boolean addEmployee(Employee employee) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_QUERY)) {

            pst.setInt(1, employee.getEmpId()); 
            pst.setString(2, employee.getEmpName());
            pst.setInt(3, employee.getAge());
            pst.setString(4, employee.getRole());
            pst.setString(5, employee.getPhoneNumber());
            pst.setString(6, employee.getEmail());
            pst.setString(7, employee.getPassword());
            pst.setString(8, employee.getEmpPassword());

            int rowCount = pst.executeUpdate();
            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteEmployee(String empId) throws SQLException {
        String query = "DELETE FROM employee_table WHERE emp_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, empId);
            int rowsDeleted = pst.executeUpdate();
            return rowsDeleted > 0;
        }
    }
    
    // Method to get employee details by ID
    public static ResultSet getEmployeeById(String empId) throws SQLException {
        String query = "SELECT * FROM employee_table WHERE emp_id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, empId);
        return pst.executeQuery(); // Return ResultSet
    }



    // Method to update employee details
    public static boolean updateEmployee(String empId, String name, String age, String role, String phone, String email) {
        StringBuilder query = new StringBuilder("UPDATE employee_table SET ");
        boolean first = true;

        if (name != null && !name.isEmpty()) {
            query.append("emp_name = ?");
            first = false;
        }
        if (age != null && !age.isEmpty()) {
            if (!first) query.append(", ");
            query.append("age = ?");
            first = false;
        }
        if (role != null && !role.isEmpty()) {
            if (!first) query.append(", ");
            query.append("role = ?");
            first = false;
        }
        if (phone != null && !phone.isEmpty()) {
            if (!first) query.append(", ");
            query.append("phone_number = ?");
            first = false;
        }
        if (email != null && !email.isEmpty()) {
            if (!first) query.append(", ");
            query.append("email = ?");
        }

        query.append(" WHERE emp_id = ?");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query.toString())) {
            int index = 1;
            if (name != null && !name.isEmpty()) {
                pst.setString(index++, name);
            }
            if (age != null && !age.isEmpty()) {
                pst.setString(index++, age);
            }
            if (role != null && !role.isEmpty()) {
                pst.setString(index++, role);
            }
            if (phone != null && !phone.isEmpty()) {
                pst.setString(index++, phone);
            }
            if (email != null && !email.isEmpty()) {
                pst.setString(index++, email);
            }
            pst.setString(index, empId);

            int rowsUpdated = pst.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

