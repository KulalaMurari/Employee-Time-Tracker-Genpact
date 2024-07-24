package com.time_tracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

import com.util.time_tracker.DBConnection;

public class GetLast5DaysDataDAO {

    public JSONObject getLast5DaysData(String employeeName) throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        JSONObject jsonResponse = new JSONObject();

        try {
             Connection con1= DBConnection.getConnection();
            String query = "SELECT DATE(task_date) AS task_date, SUM(duration) AS total_duration "
                    + "FROM task_table "
                    + "WHERE task_date >= CURDATE() - INTERVAL 5 DAY "
                    + "AND emp_name = ? "
                    + "GROUP BY DATE(task_date) "
                    + "ORDER BY DATE(task_date) ASC";

            pst = con1.prepareStatement(query);
            pst.setString(1, employeeName);
            rs = pst.executeQuery();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (rs.next()) {
                String taskDate = sdf.format(rs.getDate("task_date"));
                int totalDurationInSeconds = rs.getInt("total_duration");
                jsonResponse.put(taskDate, totalDurationInSeconds);
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return jsonResponse;
    }
}