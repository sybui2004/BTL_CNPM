package com.mycompany.btl_cnpm.view.importedproduct;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.mycompany.btl_cnpm.model.ImportedProduct;
import com.mycompany.btl_cnpm.model.Product;
import com.mycompany.btl_cnpm.model.Receipt;
import com.mycompany.btl_cnpm.view.product.ProductFrm;

public class ImportProductFrm extends JFrame implements ActionListener {
    private JPanel mainPanel, headerPanel, formPanel, buttonPanel;
    private JLabel lblTitle, lblUserInfo;
    private JLabel lblProductID, lblProductName, lblProductDesc;
    private JLabel txtProductID, txtProductName, txtProductDesc;
    private JLabel lblPrice, lblQuantity;
    private JTextField txtPrice, txtQuantity;
    private JButton btnAddToReceipt, btnCancel;
    private Receipt receipt;
    private ImportedProduct currentImportedProduct;
    
    public ImportProductFrm(Receipt receipt) {
        this.receipt = receipt;
        int lastIndex = receipt.getImportedProducts().size() - 1;
        this.currentImportedProduct = receipt.getImportedProducts().get(lastIndex);
        initComponents();
    }
    
    private void initComponents() {
        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        
        lblTitle = new JLabel("Import Product");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(lblTitle, BorderLayout.WEST);
        
        // User Info
        lblUserInfo = new JLabel("Logged in as: " + receipt.getUser().getFullname());
        lblUserInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        headerPanel.add(lblUserInfo, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Form Panel
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(240, 240, 240));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Product Information"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        // Product info panel
        JPanel productInfoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        productInfoPanel.setBackground(new Color(240, 240, 240));
        
        lblProductID = new JLabel("Product ID:");
        lblProductID.setFont(new Font("Arial", Font.BOLD, 14));
        productInfoPanel.add(lblProductID);
        
        Product product = currentImportedProduct.getProduct();
        txtProductID = new JLabel(String.valueOf(product.getId()));
        txtProductID.setFont(new Font("Arial", Font.PLAIN, 14));
        productInfoPanel.add(txtProductID);
        
        lblProductName = new JLabel("Product Name:");
        lblProductName.setFont(new Font("Arial", Font.BOLD, 14));
        productInfoPanel.add(lblProductName);
        
        txtProductName = new JLabel(product.getName());
        txtProductName.setFont(new Font("Arial", Font.PLAIN, 14));
        productInfoPanel.add(txtProductName);
        
        lblProductDesc = new JLabel("Description:");
        lblProductDesc.setFont(new Font("Arial", Font.BOLD, 14));
        productInfoPanel.add(lblProductDesc);
        
        txtProductDesc = new JLabel(product.getDescription());
        txtProductDesc.setFont(new Font("Arial", Font.PLAIN, 14));
        productInfoPanel.add(txtProductDesc);
        
        formPanel.add(productInfoPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        // Import details panel
        JPanel importDetailsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        importDetailsPanel.setBackground(new Color(240, 240, 240));
        importDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Import Details"));
        
        lblPrice = new JLabel("Unit Price:");
        lblPrice.setFont(new Font("Arial", Font.BOLD, 14));
        importDetailsPanel.add(lblPrice);
        
        txtPrice = new JTextField();
        txtPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        importDetailsPanel.add(txtPrice);
        
        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Arial", Font.BOLD, 14));
        importDetailsPanel.add(lblQuantity);
        
        txtQuantity = new JTextField();
        txtQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
        importDetailsPanel.add(txtQuantity);
        
        formPanel.add(importDetailsPanel);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button Panel
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnAddToReceipt = new JButton("Add To Receipt");
        btnAddToReceipt.setFont(new Font("Arial", Font.BOLD, 14));
        btnAddToReceipt.setPreferredSize(new Dimension(150, 40));
        btnAddToReceipt.setBackground(new Color(70, 130, 180));
        btnAddToReceipt.setForeground(Color.WHITE);
        btnAddToReceipt.addActionListener(this);
        buttonPanel.add(btnAddToReceipt);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setPreferredSize(new Dimension(120, 40));
        btnCancel.addActionListener(this);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(650, 450);
        this.setLocationRelativeTo(null);
        this.setTitle("Import Product");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddToReceipt) {
            try {
                if (txtPrice.getText().trim().isEmpty() || txtQuantity.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields!");
                    return;
                }
                
                int price = Integer.parseInt(txtPrice.getText().trim());
                int quantity = Integer.parseInt(txtQuantity.getText().trim());
                
                if (price <= 0) {
                    JOptionPane.showMessageDialog(this, "Price must be greater than 0!");
                    return;
                }
                
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be greater than 0!");
                    return;
                }
                
                currentImportedProduct.setQuantity(quantity);
                currentImportedProduct.setUnitPrice(price);
                
                ProductFrm productFrm = new ProductFrm(receipt);
                productFrm.setVisible(true);
                this.dispose();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid price and quantity values!");
            }
        } else if (e.getSource() == btnCancel) {
            receipt.getImportedProducts().remove(currentImportedProduct);
            
            ProductFrm productFrm = new ProductFrm(receipt);
            productFrm.setVisible(true);
            this.dispose();
        }
    }
} 