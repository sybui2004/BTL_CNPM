/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MSI-PC
 */
public class DAO {
    protected static Connection conn;
    
    public DAO() {
        if(conn == null) {
            String dbUrl = 
                "jdbc:mysql://localhost:3306/product_management?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                
                Class.forName(dbClass);
                conn = DriverManager.getConnection(dbUrl, "root", "root");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
