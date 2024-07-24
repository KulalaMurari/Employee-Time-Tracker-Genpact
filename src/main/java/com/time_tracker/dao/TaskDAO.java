package com.time_tracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.time_tracker.DBConnection;

public class TaskDAO {

    public static ResultSet getTasksByDate(String date) {
        String query = "SELECT * FROM task_table WHERE task_date = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, date);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getTaskById(String taskId, String date) {
        String query = "SELECT * FROM task_table WHERE task_id = ? AND task_date = ?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, taskId);
            pst.setString(2, date);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
