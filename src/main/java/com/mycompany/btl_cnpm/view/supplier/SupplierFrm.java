package com.mycompany.btl_cnpm.view.supplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import com.mycompany.btl_cnpm.dao.SupplierDAO;
import com.mycompany.btl_cnpm.model.Supplier;
import com.mycompany.btl_cnpm.model.User;
import com.mycompany.btl_cnpm.model.Receipt;
import com.mycompany.btl_cnpm.view.product.ProductFrm;

public class SupplierFrm extends JFrame implements ActionListener {
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtTel;
    private JButton btnSearch;
    private JButton btnAddNew;
    private JTable tblSupplier;
    private User user;
    
    public SupplierFrm(User user) {

        this.user = user;
        initComponents();
    }
    
    private void initComponents() {
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240));
        
        // Title panel 
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel lblTitle = new JLabel("Search Supplier");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(lblTitle);
        
        // User info panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(240, 240, 240));
        JLabel lblLoggedIn = new JLabel("Logged in as: " + user.getFullname());
        lblLoggedIn.setFont(new Font("Arial", Font.ITALIC, 12));
        userPanel.add(lblLoggedIn);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(userPanel, BorderLayout.EAST);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(240, 240, 240));
        
        formPanel.add(Box.createVerticalStrut(40));
        
        // Form fields
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        fieldsPanel.setBackground(new Color(240, 240, 240));
        
        JPanel pnLblName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnLblName.setBackground(new Color(240, 240, 240));
        JLabel lblSupplierName = new JLabel("Name:");
        pnLblName.add(lblSupplierName);
        
        JPanel pnLblAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnLblAddress.setBackground(new Color(240, 240, 240));
        JLabel lblAddress = new JLabel("Address:");
        pnLblAddress.add(lblAddress);
        
        JPanel pnLblTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnLblTel.setBackground(new Color(240, 240, 240));
        JLabel lblTel = new JLabel("Tel:");
        pnLblTel.add(lblTel);
        
        JPanel pnTxtName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTxtName.setBackground(new Color(240, 240, 240));
        txtName = new JTextField(30);
        pnTxtName.add(txtName);
        
        JPanel pnTxtAddress = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTxtAddress.setBackground(new Color(240, 240, 240));
        txtAddress = new JTextField(30);
        pnTxtAddress.add(txtAddress);
        
        JPanel pnTxtTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTxtTel.setBackground(new Color(240, 240, 240));
        txtTel = new JTextField(30);
        pnTxtTel.add(txtTel);
        
        fieldsPanel.add(pnLblName);
        fieldsPanel.add(pnTxtName);
        fieldsPanel.add(pnLblAddress);
        fieldsPanel.add(pnTxtAddress);
        fieldsPanel.add(pnLblTel);
        fieldsPanel.add(pnTxtTel);
        
        formPanel.add(fieldsPanel);
        formPanel.add(Box.createVerticalStrut(20));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(100, 25));
        btnAddNew = new JButton("Add New");
        btnAddNew.setPreferredSize(new Dimension(100, 25));
        
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnAddNew);

        formPanel.add(buttonPanel);
        
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        tablePanel.setBackground(new Color(240, 240, 240));

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableModel.addColumn("STT");
        tableModel.addColumn("Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Tell");
        
        tblSupplier = new JTable(tableModel);
        tblSupplier.setRowHeight(25);
        tblSupplier.setGridColor(new Color(200, 200, 200));
        tblSupplier.setSelectionBackground(new Color(200, 220, 240));
        tblSupplier.setBackground(Color.WHITE);
        
        JTableHeader header = tblSupplier.getTableHeader();
        header.setBackground(new Color(220, 220, 220));
        header.setFont(new Font("Arial", Font.BOLD, 12));

        TableColumnModel columnModel = tblSupplier.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);    // STT
        columnModel.getColumn(1).setPreferredWidth(150);   // Name
        columnModel.getColumn(2).setPreferredWidth(200);   // Address
        columnModel.getColumn(3).setPreferredWidth(100);   // Tell
        
        tblSupplier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click
                    Supplier selectedSupplier = getSelectedSupplier();
                    Receipt receipt = new Receipt(selectedSupplier, user, "");
                    if (selectedSupplier != null) {
                        ProductFrm productFrm = new ProductFrm(receipt);
                        productFrm.setVisible(true);
                        dispose();
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblSupplier);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);
        
        btnSearch.addActionListener(this);
        btnAddNew.addActionListener(this);
        
        this.setContentPane(mainPanel);
        this.setSize(650, 550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Search Supplier");
    }
    
    private Supplier getSelectedSupplier() {
        int selectedRow = tblSupplier.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a supplier");
            return null;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblSupplier.getModel();
        String name = (String) model.getValueAt(selectedRow, 1);
        String address = (String) model.getValueAt(selectedRow, 2);
        String tel = (String) model.getValueAt(selectedRow, 3);
        
        SupplierDAO supplierDAO = new SupplierDAO();
        ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName(name);
        for (Supplier s : suppliers) {
            if (s.getName().equals(name) && s.getAddress().equals(address) && s.getTel().equals(tel)) {
                return s;
            }
        }
        
        return null;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            // Implement search functionality
            String name = txtName.getText();
            SupplierDAO supplierDAO = new SupplierDAO();
            DefaultTableModel model = (DefaultTableModel) tblSupplier.getModel();
            model.setRowCount(0);
            
            ArrayList<Supplier> suppliers = supplierDAO.searchSupplierByName(name);
            if (suppliers.isEmpty()) {
                // Show alert when no suppliers found
                JOptionPane.showMessageDialog(this, 
                    "No suppliers found matching \"" + name + "\"", 
                    "Search Results", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            int stt = 1;
            for (Supplier supplier : suppliers) {
                model.addRow(new Object[]{
                    stt++,
                    supplier.getName(),
                    supplier.getAddress(),
                    supplier.getTel()
                });
            }
        } else if (e.getSource() == btnAddNew) {
            // Implement add new functionality
            if (txtName.getText().trim().isEmpty() || 
                txtAddress.getText().trim().isEmpty() || 
                txtTel.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Please fill all fields");
                return;
            }
            
            Supplier supplier = new Supplier();
            supplier.setName(txtName.getText());
            supplier.setAddress(txtAddress.getText());
            supplier.setTel(txtTel.getText());
            
            SupplierDAO supplierDAO = new SupplierDAO();
            if (supplierDAO.addSupplier(supplier)) {
                JOptionPane.showMessageDialog(this, "Supplier added successfully");
                
                // Clear fields
                txtName.setText("");
                txtAddress.setText("");
                txtTel.setText("");
                
                // Refresh supplier list
                DefaultTableModel model = (DefaultTableModel) tblSupplier.getModel();
                model.setRowCount(0);
                
                int stt = 1;
                for (Supplier s : supplierDAO.searchSupplierByName("")) {
                    model.addRow(new Object[]{
                        stt++,
                        s.getName(),
                        s.getAddress(),
                        s.getTel()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add supplier");
            }
        }
    }
} 