/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author MSI-PC
 */
public class Receipt implements Serializable {
    private int id;
    private Date date;
    private int totalPrice;
    private int totalProductQuantity;
    private String note;
    private Supplier supplier;
    private User user;
    private ArrayList<ImportedProduct> importedProducts;
    
    public Receipt() {
        super();
        this.date = new Date();
        this.importedProducts = new ArrayList<>();
    }
    
    public Receipt(Supplier supplier, User user, String note) {
        super();
        this.date = new Date();
        this.supplier = supplier;
        this.user = user;
        this.note = note;
        this.importedProducts = new ArrayList<>();
        this.totalPrice = 0;
        this.totalProductQuantity = 0;
    }
    
    public void addImportedProduct(ImportedProduct importedProduct) {
        this.importedProducts.add(importedProduct);
        this.totalPrice += importedProduct.getSubTotal();
        this.totalProductQuantity += importedProduct.getQuantity();
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalProductQuantity() {
        return totalProductQuantity;
    }

    public void setTotalProductQuantity(int totalProductQuantity) {
        this.totalProductQuantity = totalProductQuantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<ImportedProduct> getImportedProducts() {
        return importedProducts;
    }
}
