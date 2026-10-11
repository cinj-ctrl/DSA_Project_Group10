/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_group10_realestate;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.*;

/**
 *
 * @author cassandra
 */
public class Client extends JPanel {
    
    private JPanel pnlClientForm, pnlClientTable;
    private JLabel lblClientTitle, lblClientID, lblClientName, lblContactInfo, lblPropertiesOwned, lblTableTitle;
    private JTextField txfClientID, txfClientName, txfContactInfo, txfPropertiesOwned;
    private JButton btnAddClient;
    private JTable tblClientRecords;
    
    public Client() {
        setBounds(0, 0, 1000, 600);
        setLayout(null);
        
        add(pnlClientForm);
        add(pnlClientTable);
        
        
        //L panels
        pnlClientForm = new JPanel();
        pnlClientForm.setLayout(null);
        pnlClientForm.setBounds(10, 75, 310, 450);
        pnlClientForm.setBackground(Color.white);
        pnlClientForm.setVisible(true);
        add(pnlClientForm);
        
        //R panels
        pnlClientTable = new JPanel();
        pnlClientTable.setLayout(null);
        pnlClientTable.setBounds(330, 75, 655, 450);
        pnlClientTable.setBackground(Color.white);
        pnlClientTable.setVisible(true);
        add(pnlClientTable);
        
        //components
        //L panels Labels
        lblClientTitle = new JLabel("Manage Client");
        lblClientTitle.setBounds(10, 10, 150, 25);
        lblClientTitle.add(lblClientTitle);
        
        lblClientID = new JLabel("Client ID");
        lblClientID.setBounds(10, 70, 100, 25);
        lblClientID.add(lblClientID);
        
        lblClientName = new JLabel("Name");
        lblClientName.setBounds(10, 120, 100, 25);
        lblClientName.add(lblClientName);
        
        lblContactInfo = new JLabel("Contact Info");
        lblContactInfo.setBounds(10, 170, 100, 25);
        lblContactInfo.add(lblContactInfo);
        
        lblPropertiesOwned = new JLabel("Properties Owned");
        lblPropertiesOwned.setBounds(10, 220, 130, 25);
        lblPropertiesOwned.add(lblPropertiesOwned);
                
        //L panels TextF
        txfClientID = new JTextField();
        txfClientID.setBounds(140, 70, 150, 25);
        txfClientID.add(txfClientID);
        
        txfClientName = new JTextField();
        txfClientName.setBounds(140, 120, 150, 25);
        txfClientName.add(txfClientName);
        
                txfContactInfo = new JTextField();
        txfContactInfo.setBounds(140, 170, 150, 25);
        txfContactInfo.add(txfContactInfo);
         
        txfPropertiesOwned = new JTextField();
        txfPropertiesOwned.setBounds(140, 220, 150, 25);
        txfPropertiesOwned.add(txfPropertiesOwned);
        
        //Button
        btnAddClient = new JButton("Add Client");
        btnAddClient.setBounds(10, 280, 280, 30);
        btnAddClient.add(btnAddClient);
        
        //Panel Table
        tblClientRecords = new JTable();
        tblClientRecords.setBounds(10, 45, 635, 390);
        tblClientRecords.add(tblClientRecords);
    
        
    }
    
}
