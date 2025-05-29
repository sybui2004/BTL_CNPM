/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.btl_cnpm.dao;

import com.mycompany.btl_cnpm.model.User;
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
public class UserDAOTest {
    
    private UserDAO userDAO;
    
    public UserDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
    }
    
    @AfterEach
    public void tearDown() {
    }
    @Test
    public void testCheckLoginSuccess() {
        User user = new User();
        user.setUsername("a"); 
        user.setPassword("a@123");
        
        boolean result = userDAO.checkLogin(user);
        assertTrue(result);
       
        assertNotNull(user.getFullname());
        assertNotNull(user.getRole());
        assertTrue(user.getId() > 0);
    }
    @Test
    public void testCheckLoginFail() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        
        boolean result = userDAO.checkLogin(user);
      
        assertFalse(result);
    }
}
