/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MSI-PC
 */
public class UserDAO extends DAO {
    
    public UserDAO() {
        super();
    }
    
    public boolean checkLogin(User user) {
        boolean result = false;
        String sql = "SELECT * FROM tblUser WHERE username=? AND password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                user.setId(rs.getInt("id"));
                String fullname = rs.getString("fullname");
                user.setFullname(fullname);
                user.setRole(rs.getString("role"));
                result = true;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
