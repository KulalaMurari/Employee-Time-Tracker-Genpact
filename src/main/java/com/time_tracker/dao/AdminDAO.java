package com.time_tracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.util.time_tracker.DBConnection;
import com.time_tracker.model.Admin;

public class AdminDAO {
    
    private static final String QUERY = "SELECT * FROM admin_details WHERE admin_name = ? AND admin_password = ?";

    public Admin getAdminByUsernameAndPassword(String username, String password) {
        Admin admin = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(QUERY)) {
             
            pst.setString(1, username);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setAdminName(rs.getString("admin_name"));
                // Add more fields if necessary
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}
