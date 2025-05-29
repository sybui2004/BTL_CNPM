/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.ImportedProduct;
import com.mycompany.btl_cnpm.model.Product;
import com.mycompany.btl_cnpm.model.Receipt;
import com.mycompany.btl_cnpm.model.Supplier;
import com.mycompany.btl_cnpm.model.User;
import java.util.Date;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 *
 * @author MSI-PC
 */
public class ReceiptDAOTest {
    
    private ReceiptDAO receiptDAO;
    private SupplierDAO supplierDAO;
    private ProductDAO productDAO;
    private UserDAO userDAO;
    public ReceiptDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        receiptDAO = new ReceiptDAO();
        supplierDAO = new SupplierDAO();
        productDAO = new ProductDAO();
        userDAO = new UserDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddProductOrderSuccess() {        
        User user = new User();
        user.setUsername("a");
        user.setPassword("a@123"); 
        boolean loginSuccess = userDAO.checkLogin(user);
        assertTrue(loginSuccess);

        ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName("An");
        assertFalse(suppliers.isEmpty());
        Supplier supplier = suppliers.get(0);

        ArrayList<Product> products = productDAO.searchProductByName("Pen");
        assertFalse(products.isEmpty());
        Product product = products.get(0);
        
        Receipt receipt = new Receipt();
        receipt.setDate(new Date());
        receipt.setNote("Test receipt");
        receipt.setSupplier(supplier);
        receipt.setUser(user);
        
        ImportedProduct importedProduct = new ImportedProduct(10, 1000, product);
        receipt.addImportedProduct(importedProduct);
        
        boolean result = receiptDAO.addProductOrder(receipt);
        
        assertTrue(result);
        
        assertTrue(receipt.getId() > 0);
    }
    
    @Test
    public void testAddProductOrderFailWithNoProducts() {
        User user = new User();
        user.setUsername("a");
        user.setPassword("a@123"); 
        boolean loginSuccess = userDAO.checkLogin(user);
        assertTrue(loginSuccess);

        ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName("An");
        assertFalse(suppliers.isEmpty());
        Supplier supplier = suppliers.get(0);
        
        Receipt receipt = new Receipt();
        receipt.setDate(new Date());
        receipt.setNote("Empty receipt");
        receipt.setSupplier(supplier);
        receipt.setUser(user);
        
        boolean result = receiptDAO.addProductOrder(receipt);
        
        assertFalse(result);
    }
}
