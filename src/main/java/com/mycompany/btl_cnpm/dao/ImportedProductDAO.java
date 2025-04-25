/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.ImportedProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author MSI-PC
 */
public class ImportedProductDAO extends DAO {

    public ImportedProductDAO() {
        super();
    }
    
    public boolean addImportedProduct(ImportedProduct importedProduct, int receiptId) {
        String sql = "INSERT INTO tblImportedProduct(idProduct, quantity, unitPrice, subTotal, idReceipt) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, importedProduct.getProduct().getId());
            ps.setInt(2, importedProduct.getQuantity());
            ps.setInt(3, importedProduct.getUnitPrice());
            ps.setInt(4, importedProduct.getSubTotal());
            ps.setInt(5, receiptId);
            ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                importedProduct.setId(generatedKeys.getInt(1));
            }
            generatedKeys.close();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
