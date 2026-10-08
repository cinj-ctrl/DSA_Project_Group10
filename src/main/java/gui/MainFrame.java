/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gui;



import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Cindrake Josh
 */
public class MainFrame extends JFrame {

    private JButton btnProperties;
    private JButton btnTransaction;
    private JButton btnClient;
    private JButton btnReports;
    
    private JLabel hdrRealEstatePro;
    private JPanel pnlHeader;
    
            
    public MainFrame() {
        
        TransactionPanel transactionPanel = new TransactionPanel();
        
        setTitle("Real state Management System");
        setSize(1000, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
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
        
        btnProperties = new JButton("Transactions");
        btnProperties.setBounds(610, 15, 120, 40);
        pnlHeader.add(btnProperties);
        
        btnProperties = new JButton("Clients");
        btnProperties.setBounds(740, 15, 100, 40);
        pnlHeader.add(btnProperties);
        
        btnProperties = new JButton("Reports");
        btnProperties.setBounds(850, 15, 100, 40);
        pnlHeader.add(btnProperties);
        
        add(transactionPanel);
    }
    }

    
