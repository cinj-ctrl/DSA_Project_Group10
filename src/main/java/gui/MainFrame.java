/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gui;

import gui.propertiesGUI;
import gui.ReportControls;
import gui.TransactionPanel;
import gui.TransactionPanel;
import gui.propertiesGUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author Cindrake Josh
 */
public class MainFrame extends JFrame implements ActionListener{

    private JButton btnProperties;
    private JButton btnTransaction;
    private JButton btnClient;
    private JButton btnReports;
    
    private JLabel hdrRealEstatePro;
    private JPanel pnlHeader;
    private JPanel defPanel;
    
    private JPanel pnlLogin;
    private JLabel lblLoginTitle, lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    propertiesGUI properties = new propertiesGUI();
    TransactionPanel transactionPanel = new TransactionPanel();
    ReportControls rcontrols = new ReportControls();
    Client client = new Client();
    
            
    public MainFrame() {
        setTitle("Real state Management System");
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initLoginPanel();
        
    }
    
    private void initLoginPanel() {
        pnlLogin = new JPanel();
        pnlLogin.setLayout(null);
        pnlLogin.setBounds(0, 0, 1000, 600);

        lblLoginTitle = new JLabel("System Login");
        lblLoginTitle.setBounds(430, 150, 150, 30);
        pnlLogin.add(lblLoginTitle);

        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(350, 220, 80, 25);
        pnlLogin.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(440, 220, 200, 25);
        pnlLogin.add(txtUsername);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(350, 270, 80, 25);
        pnlLogin.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(440, 270, 200, 25);
        pnlLogin.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(450, 330, 100, 35);
        btnLogin.addActionListener(this);
        pnlLogin.add(btnLogin);

        add(pnlLogin);
    }
    
    private void initMainDashboard() {
        setTitle("Real Estate Management System - Dashboard");
        
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null);
        pnlHeader.setBackground(Color.gray);
        pnlHeader.setBounds(0, 0, 1000, 70);
        add(pnlHeader);
        
        hdrRealEstatePro = new JLabel("RealEstate Pro");
        hdrRealEstatePro.setBounds(10, 15, 200, 50);
        pnlHeader.add(hdrRealEstatePro);
        
        btnProperties = new JButton("Properties");
        btnProperties.setBounds(500, 15, 100, 40);
        pnlHeader.add(btnProperties);
        
        btnTransaction = new JButton("Transactions");
        btnTransaction.setBounds(610, 15, 120, 40);
        pnlHeader.add(btnTransaction);
        
        btnClient = new JButton("Clients");
        btnClient.setBounds(740, 15, 100, 40);
        pnlHeader.add(btnClient);
        
        btnReports = new JButton("Reports");
        btnReports.setBounds(850, 15, 100, 40);
        pnlHeader.add(btnReports);
        
        properties.setBounds(0, 70, 1000, 530);
        transactionPanel.setBounds(0, 70, 1000, 530);
        rcontrols.setBounds(0, 70, 1000, 530);
        client.setBounds(0, 70, 1000, 530);
        
        btnProperties.addActionListener(this);
        btnTransaction.addActionListener(this);
        btnClient.addActionListener(this);
        btnReports.addActionListener(this);
        
//        add(properties);
//        add(transactionPanel);
//        add(rcontrols);
//        add(client);
        
        defPanel = properties;
        add(defPanel);
        
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle Login Click
        if (e.getSource() == btnLogin) {
            String user = txtUsername.getText().trim();
            String pass = new String(txtPassword.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (authenticate(user, pass)) {
                remove(pnlLogin); // Remove login screen
                initMainDashboard(); // Load main window contents
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }
        
        if (defPanel != null) {
            remove(defPanel);
        }
        
        if (e.getSource() == btnProperties) {
            defPanel = properties;
        } else if (e.getSource() == btnTransaction) {
            defPanel = transactionPanel;
        } else if (e.getSource() == btnClient) {
            defPanel = client;
        } else if (e.getSource() == btnReports) {
            defPanel = rcontrols;
        }
        
        add(defPanel); 
        revalidate();      
        repaint();
    }
    
    private boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/realestate_db", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Connection Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
    
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
////            new MainFrame().setVisible(true);
//        });
    
    
    
    

