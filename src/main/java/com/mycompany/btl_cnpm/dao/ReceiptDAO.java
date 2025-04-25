/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.ImportedProduct;
import com.mycompany.btl_cnpm.model.Receipt; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class ReceiptDAO extends DAO {
    
    public ReceiptDAO() {
        super();
    }
    
    public boolean addProductOrder(Receipt receipt) {
        String sqlAddProductOrder = "INSERT INTO tblReceipt(date, totalPrice, totalProductQuantity, note, idSupplier, idUser) VALUES(?,?,?,?,?,?)";
        String sqlUpdateProductQuantity = "UPDATE tblProduct SET quantity = quantity + ? WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlAddProductOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, new Timestamp(receipt.getDate().getTime()));
            ps.setInt(2, receipt.getTotalPrice());
            ps.setInt(3, receipt.getTotalProductQuantity());
            ps.setString(4, receipt.getNote());
            ps.setInt(5, receipt.getSupplier().getId());
            ps.setInt(6, receipt.getUser().getId());
            ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                receipt.setId(generatedKeys.getInt(1));

                ps = conn.prepareStatement(sqlUpdateProductQuantity);
                ImportedProductDAO importedProductDAO = new ImportedProductDAO();
                ArrayList<ImportedProduct> importedProducts = receipt.getImportedProducts();
                for (int i = 0; i < importedProducts.size(); i++) {
                    importedProductDAO.addImportedProduct(importedProducts.get(i), receipt.getId());         
                    ps.setInt(1, importedProducts.get(i).getQuantity());
                    ps.setInt(2, importedProducts.get(i).getProduct().getId());
                    ps.executeUpdate();
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
