package com.mycompany.btl_cnpm.view.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import com.mycompany.btl_cnpm.dao.ProductDAO;
import com.mycompany.btl_cnpm.model.Product;
import com.mycompany.btl_cnpm.model.ImportedProduct;
import com.mycompany.btl_cnpm.model.Receipt;
import com.mycompany.btl_cnpm.view.importedproduct.ImportProductFrm;
import com.mycompany.btl_cnpm.view.receipt.ConfirmFrm;

public class ProductFrm extends JFrame implements ActionListener {
    private JTextField txtName;
    private JTextField txtDescription;
    private JButton btnSearch;
    private JButton btnAddNew;
    private JTable tblProduct;
    private DefaultTableModel tableModel;
    private Receipt receipt;
    
    public ProductFrm(Receipt receipt) {
        this.receipt = receipt;
        initComponents();
    }
    
    private void initComponents() {
        // Main panel
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout(10, 10));
        pnMain.setBackground(new Color(240, 240, 240));
        pnMain.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        
        // Title panel
        JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTitle.setBackground(new Color(240, 240, 240));
        JLabel lblTitle = new JLabel("Search Product");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        pnTitle.add(lblTitle);
        
        // User info panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(240, 240, 240));
        JLabel lblLoggedIn = new JLabel("Logged in as: " + receipt.getUser().getFullname());
        lblLoggedIn.setFont(new Font("Arial", Font.PLAIN, 12));
        userPanel.add(lblLoggedIn);
        
        headerPanel.add(pnTitle, BorderLayout.CENTER);
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        // Supplier info panel
        JPanel pnSupplier = new JPanel();
        pnSupplier.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnSupplier.setBackground(new Color(240, 240, 240));
        JLabel lblSupplier = new JLabel("Supplier: " + receipt.getSupplier().getName() + " - " + receipt.getSupplier().getAddress());
        lblSupplier.setFont(new Font("Arial", Font.ITALIC, 14));
        pnSupplier.add(lblSupplier);
        
        JPanel pnNorth = new JPanel();
        pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));
        pnNorth.setBackground(new Color(240, 240, 240));
        pnNorth.add(headerPanel);
        pnNorth.add(pnSupplier);
        
        pnMain.add(pnNorth, BorderLayout.NORTH);
        
        // Search panel
        JPanel pnSearch = new JPanel();
        pnSearch.setLayout(new BoxLayout(pnSearch, BoxLayout.Y_AXIS));
        pnSearch.setBackground(new Color(240, 240, 240));
        pnSearch.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Input fields panel
        JPanel pnInputRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnInputRow1.setBackground(new Color(240, 240, 240));
        JLabel lblName = new JLabel("Name:", SwingConstants.RIGHT);
        lblName.setPreferredSize(new Dimension(100, 25));
        pnInputRow1.add(lblName);
        
        txtName = new JTextField(30);
        pnInputRow1.add(txtName);
        
        pnSearch.add(pnInputRow1);
        
        // Description input field
        JPanel pnInputRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnInputRow2.setBackground(new Color(240, 240, 240));
        JLabel lblDesc = new JLabel("Description:", SwingConstants.RIGHT);
        lblDesc.setPreferredSize(new Dimension(100, 25));
        pnInputRow2.add(lblDesc);
        
        txtDescription = new JTextField(30);
        pnInputRow2.add(txtDescription);
        
        pnSearch.add(pnInputRow2);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(100, 25));
        btnSearch.addActionListener(this);
        
        btnAddNew = new JButton("Add New");
        btnAddNew.setPreferredSize(new Dimension(100, 25));
        btnAddNew.addActionListener(this);
        
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnAddNew);
        
        pnSearch.add(buttonPanel);
        
        pnMain.add(pnSearch, BorderLayout.CENTER);
        
        // Table panel
        JPanel pnTable = new JPanel();
        pnTable.setLayout(new BorderLayout());
        pnTable.setBackground(new Color(240, 240, 240));
        pnTable.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableModel.addColumn("STT");
        tableModel.addColumn("Name");
        tableModel.addColumn("Description");
        
        tblProduct = new JTable(tableModel);
        tblProduct.setRowHeight(25);
        tblProduct.setGridColor(new Color(200, 200, 200));
        tblProduct.setSelectionBackground(new Color(200, 220, 240));
        tblProduct.setBackground(Color.WHITE);
        
        tblProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navigateToImportProduct();
            }
        });
        
        JTableHeader header = tblProduct.getTableHeader();
        header.setBackground(new Color(220, 220, 220));
        header.setFont(new Font("Arial", Font.BOLD, 12));
        
        JScrollPane scrollPane = new JScrollPane(tblProduct);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        pnTable.add(scrollPane, BorderLayout.CENTER);
        
        JPanel pnSouth = new JPanel(new BorderLayout());
        pnSouth.setBackground(new Color(240, 240, 240));
        pnSouth.add(pnTable, BorderLayout.CENTER);
        
        // Add Next button panel
        JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nextButtonPanel.setBackground(new Color(240, 240, 240));
        JButton btnNext = new JButton("Next");
        btnNext.setPreferredSize(new Dimension(100, 30));
        btnNext.addActionListener(this);
        nextButtonPanel.add(btnNext);
        pnSouth.add(nextButtonPanel, BorderLayout.SOUTH);
        
        pnMain.add(pnSouth, BorderLayout.SOUTH);
        
        this.setContentPane(pnMain);
        this.setSize(650, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Search Product");
    }
    private void navigateToImportProduct() {
        Product selectedProduct = getSelectedProduct();
        if (selectedProduct != null) {
            ImportedProduct importedProduct = new ImportedProduct(0, 0, selectedProduct);
            receipt.addImportedProduct(importedProduct);
            
            ImportProductFrm importFrm = new ImportProductFrm(receipt);
            importFrm.setVisible(true);
            this.dispose();
        }
    }
    
    private Product getSelectedProduct() {
        int selectedRow = tblProduct.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product");
            return null;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
        String name = (String) model.getValueAt(selectedRow, 1);
        String description = (String) model.getValueAt(selectedRow, 2);        
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> products = productDAO.searchProductByName(name);
        for (Product p : products) {
            if (p.getName().equals(name) && p.getDescription().equals(description)) {
                return p;
            }
        }
        
        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            String name = txtName.getText();
            
            ProductDAO productDAO = new ProductDAO();
            DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
            model.setRowCount(0);
            
            ArrayList<Product> products = productDAO.searchProductByName(name);
            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No products found matching \"" + name + "\"", 
                    "Search Results", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            int stt = 1;
            for (Product product : products) {
                model.addRow(new Object[] {
                    stt++,
                    product.getName(),
                    product.getDescription(),
                });
            }
        } else if (e.getSource() == btnAddNew) {
            if (txtName.getText().trim().isEmpty() || 
                txtDescription.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            
            Product product = new Product();
            product.setName(txtName.getText());
            product.setDescription(txtDescription.getText());
            
            ProductDAO productDAO = new ProductDAO();
            if (productDAO.addProduct(product)) {
                JOptionPane.showMessageDialog(this, "Product added successfully");
    
                txtName.setText("");
                txtDescription.setText("");

                btnSearch.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product");
            }
        } else if (e.getSource() instanceof JButton && ((JButton)e.getSource()).getText().equals("Next")) {
            if (receipt.getImportedProducts().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select at least one product", 
                    "Warning", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            ConfirmFrm confirmFrm = new ConfirmFrm(receipt);
            confirmFrm.setVisible(true);
            this.dispose();
        }
    }
} 