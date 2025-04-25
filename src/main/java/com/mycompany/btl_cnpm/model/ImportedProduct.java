/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.btl_cnpm.model;

import java.io.Serializable;

/**
 *
 * @author MSI-PC
 */
public class ImportedProduct implements Serializable {
    private int id;
    private int quantity;
    private int subTotal;
    private int unitPrice;
    private Product product;
    
    public ImportedProduct() {
        super();
    }
    
    public ImportedProduct(int quantity, int unitPrice, Product product) {
        super();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.subTotal = quantity * unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subTotal = quantity * unitPrice;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
        this.subTotal = quantity * unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
