/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class SupplierDAO extends DAO {

    public SupplierDAO() {
        super();
    }
    
    public ArrayList<Supplier> searchSupplierByName(String name) {
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
        String sql = "SELECT * FROM tblSupplier WHERE name LIKE ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setTel(rs.getString("tel"));
                suppliers.add(supplier);
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }
    
    public boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO tblSupplier(name, address, tel) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getAddress());
            ps.setString(3, supplier.getTel());
            ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                supplier.setId(generatedKeys.getInt(1));
            }
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
