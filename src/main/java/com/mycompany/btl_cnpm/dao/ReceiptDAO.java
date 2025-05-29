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
        if (receipt.getImportedProducts() == null || receipt.getImportedProducts().isEmpty()) {
            return false;
        }
        String sqlAddProductOrder = "INSERT INTO tblReceipt(idUser, idSupplier, date, note ) VALUES(?,?,?,?)";
        String sqlAddImportedProduct = "INSERT INTO tblImportedProduct(idReceipt, idProduct, quantity, unitPrice) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlAddProductOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, receipt.getUser().getId());
            ps.setInt(2, receipt.getSupplier().getId());
            ps.setTimestamp(3, new Timestamp(receipt.getDate().getTime()));
            ps.setString(4, receipt.getNote());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                receipt.setId(generatedKeys.getInt(1));

                ArrayList<ImportedProduct> importedProducts = receipt.getImportedProducts();
                for (ImportedProduct importedProduct : importedProducts) {
                    ps = conn.prepareStatement(sqlAddImportedProduct);
                    ps.setInt(1, receipt.getId());
                    ps.setInt(2, importedProduct.getProduct().getId());
                    ps.setInt(3, importedProduct.getQuantity());
                    ps.setInt(4, importedProduct.getUnitPrice());
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
