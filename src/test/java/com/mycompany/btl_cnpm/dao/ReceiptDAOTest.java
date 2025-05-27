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

    /**
     * Test thêm phiếu nhập hàng với sản phẩm hợp lệ
     */
    @Test
    public void testAddProductOrderSuccess() {
        System.out.println("addProductOrder - Success");
        
        // Tạo user đăng nhập
        User user = new User();
        user.setUsername("admin"); // Thay đổi thành username có trong DB
        user.setPassword("123456"); // Thay đổi thành password có trong DB
        boolean loginSuccess = userDAO.checkLogin(user);
        assertTrue(loginSuccess);
        
        // Tìm supplier
        Supplier supplier = null;
        for (Supplier s : supplierDAO.searchSupplierByName("")) {
            supplier = s;
            break;
        }
        assertNotNull(supplier);
        
        // Tìm product
        Product product = null;
        for (Product p : productDAO.searchProductByName("")) {
            product = p;
            break;
        }
        assertNotNull(product);
        
        // Tạo phiếu nhập hàng
        Receipt receipt = new Receipt();
        receipt.setDate(new Date());
        receipt.setNote("Test receipt");
        receipt.setSupplier(supplier);
        receipt.setUser(user);
        
        // Thêm sản phẩm vào phiếu nhập
        ImportedProduct importedProduct = new ImportedProduct(10, 1000, product); // Số lượng và đơn giá
        receipt.addImportedProduct(importedProduct);
        
        // Lưu phiếu nhập
        boolean result = receiptDAO.addProductOrder(receipt);
        
        // Kiểm tra lưu thành công
        assertTrue(result);
        
        // Kiểm tra phiếu nhập đã được gán ID
        assertTrue(receipt.getId() > 0);
        System.out.println("Receipt created with ID: " + receipt.getId());
    }
    
    /**
     * Test thêm phiếu nhập hàng thất bại do không có sản phẩm
     */
    @Test
    public void testAddProductOrderFailWithNoProducts() {
        System.out.println("addProductOrder - No Products");
        
        // Tạo user đăng nhập
        User user = new User();
        user.setUsername("admin"); // Thay đổi thành username có trong DB
        user.setPassword("123456"); // Thay đổi thành password có trong DB
        boolean loginSuccess = userDAO.checkLogin(user);
        assertTrue(loginSuccess);
        
        // Tìm supplier
        Supplier supplier = null;
        for (Supplier s : supplierDAO.searchSupplierByName("")) {
            supplier = s;
            break;
        }
        assertNotNull(supplier);
        
        // Tạo phiếu nhập hàng không có sản phẩm
        Receipt receipt = new Receipt();
        receipt.setDate(new Date());
        receipt.setNote("Empty receipt");
        receipt.setSupplier(supplier);
        receipt.setUser(user);
        
        // Lưu phiếu nhập
        boolean result = receiptDAO.addProductOrder(receipt);
        
        // Kiểm tra lưu thất bại vì không có sản phẩm
        assertFalse(result);
    }
    
    /**
     * Test thêm phiếu nhập hàng thất bại với dữ liệu null
     */
    @Test
    public void testAddProductOrderWithNullReceipt() {
        System.out.println("addProductOrder - Null Receipt");
        
        // Truyền tham số null
        Receipt nullReceipt = null;
        boolean result = receiptDAO.addProductOrder(nullReceipt);
        
        // Kiểm tra lưu thất bại
        assertFalse(result);
    }
}
