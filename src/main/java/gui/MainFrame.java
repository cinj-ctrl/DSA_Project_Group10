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
    
    propertiesGUI properties = new propertiesGUI();
    TransactionPanel transactionPanel = new TransactionPanel();
    ReportControls rcontrols = new ReportControls();
    Client client = new Client();
    
            
    public MainFrame() {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        remove(defPanel); 
        
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
    
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
////            new MainFrame().setVisible(true);
//        });
    }

    
    
    

