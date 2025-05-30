/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.Product;
import java.util.ArrayList;
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
public class ProductDAOTest {
    
    private ProductDAO productDAO;
    
    public ProductDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        productDAO = new ProductDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSearchProductByNameException1() {
        String key = "xxxxxxxx";
        ArrayList<Product> products = productDAO.searchProductByName(key);
        assertNotNull(products);
        assertEquals(0, products.size());
    }
    
    @Test
    public void testSearchProductByNameException2() {
        String key = "sd";
        ArrayList<Product> products = productDAO.searchProductByName(key);
        assertNotNull(products);
        assertEquals(0, products.size());
    }

    @Test
    public void testSearchProductByNameStandard1() {
        String key = "Pen";
        ArrayList<Product> products = productDAO.searchProductByName(key);
        
        assertNotNull(products);
        assertEquals(4, products.size());
        
        for(int i = 0; i < products.size(); i++){
			assertTrue(products.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
    }

    @Test
    public void testSearchProductByNameStandard2() {
        String key = "Book";
        ArrayList<Product> products = productDAO.searchProductByName(key);
        
        assertNotNull(products);
        assertEquals(2, products.size());
        
        for(int i = 0; i < products.size(); i++){
			assertTrue(products.get(i).getName().toLowerCase().contains(key.toLowerCase()));
		}
		return;
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        String uniqueName = "Test Product " + System.currentTimeMillis();
        product.setName(uniqueName);
        product.setDescription("Test description");
        
        boolean result = productDAO.addProduct(product);
 
        assertTrue(result);
        
        assertTrue(product.getId() > 0);
        
        ArrayList<Product> products = productDAO.searchProductByName(uniqueName);
        assertEquals(1, products.size());
        
        Product foundProduct = products.get(0);
        assertEquals(uniqueName, foundProduct.getName());
    }
}
