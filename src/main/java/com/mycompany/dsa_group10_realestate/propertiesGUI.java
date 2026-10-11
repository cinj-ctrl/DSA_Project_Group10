/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_group10_realestate;

/**
 *
 * @author jobelle
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author jobelle
 */
public class propertiesGUI extends JPanel implements ActionListener{
    
   
   // JTextField;
   private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
   private JLabel panLabel, propertyDetail, propertyID, Location, Price, Status, PropertyReg, searchBy;
   private JPanel panelLeft, panRight;
   private JTextField txtID, txtloc, txtprice, txtSearch;
   private JComboBox<String> cbStatus, cbSearchBy;
   private JTable regTable;
   private static final String [] columnNames = {"Property ID","Location","Price","Status"};
   private static final String [] statusArray = {"Available","Pending","Sold"};
   private static final String [] searchArray ={};
    

    
    
    propertiesGUI(){
        
        setSize(1000, 600);
        setLayout(null);
        
        panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBounds(5, 60, 400, 480);
        panelLeft.setBackground(Color.white);
        add(panelLeft);
        
        panRight = new JPanel();
        panRight.setLayout(null);
        panRight.setBounds(420, 60, 560, 480);
        panRight.setBackground(Color.white);
        add(panRight);
        
        propertyDetail = new JLabel("Property Details");
        propertyDetail.setBounds(5, 5, 100, 50);
        panelLeft.add(propertyDetail);
        
        propertyID = new JLabel("Property ID:");
        propertyID.setBounds(20, 100, 100, 50);
        panelLeft.add(propertyID);
        
        txtID = new JTextField();
        txtID.setBounds(170, 115, 200, 20);
        panelLeft.add(txtID);
        
        Location = new JLabel("Location:");
        Location.setBounds(20, 150, 100, 50);
        panelLeft.add(Location);
        
        txtloc = new JTextField();
        txtloc.setBounds(170, 165, 200, 20);
        panelLeft.add(txtloc);
        
        Price = new JLabel("Price:");
        Price.setBounds(20, 200, 100, 50);
        panelLeft.add(Price);
        
        txtprice = new JTextField();
        txtprice.setBounds(170, 215, 200, 20);
        panelLeft.add(txtprice);
        
        Status = new JLabel("Status:");
        Status.setBounds(20, 250, 100, 50);
        panelLeft.add(Status);
        
        cbStatus = new JComboBox<>(statusArray);
        cbStatus.setBounds(170, 265, 200, 20);
        panelLeft.add(cbStatus);
        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(50, 330, 80, 30);
        panelLeft.add(btnAdd);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(150, 330, 80, 30);
        panelLeft.add(btnUpdate);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(250, 330, 80, 30);
        panelLeft.add(btnDelete);
        
        
         
        PropertyReg = new JLabel("Property Registry");
        PropertyReg.setBounds(5, 5, 100, 50);
        panRight.add(PropertyReg);
        
        searchBy = new JLabel("Search By:");
        searchBy.setBounds(5, 20, 100, 50);
        panRight.add(searchBy);
        
        cbSearchBy = new JComboBox<>(searchArray);
        cbSearchBy.setBounds(70, 38, 100, 20);
        panRight.add(cbSearchBy);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(180, 38, 150, 20);
        panRight.add(txtSearch);
        
        btnSearch = new JButton("Seach");
        btnSearch.setBounds(330, 38, 70, 20);
        panRight.add(btnSearch);
        
        
        
        regTable = new JTable ();
        regTable.setBounds(5, 70, 550, 300);
        regTable.setBackground(Color.white);
        panRight.add(regTable);
        
        
        
        
        
        
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);

        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        
    }
    
}

    

