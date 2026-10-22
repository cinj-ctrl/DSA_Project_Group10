/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cassandra
 */
public class Client extends JPanel implements ActionListener{
    
    private JPanel pnlClientForm, pnlClientTable;
    private JLabel lblClientTitle, lblClientID, lblClientName, lblContactInfo, lblPropertiesOwned, lblTableTitle;
    private JTextField txfClientID, txfClientName, txfContactInfo, txfPropertiesOwned;
    private JButton btnAddClient, btnUpdateClient, btnDeleteClient, btnSearchClient;
    private JTable tblClientRecords;
    private JComboBox<String> cbSearchCllient;
    private JScrollPane scrollpane;
    private DefaultTableModel tableModel;
    private LinkedList<Object> linkedlist;
    
    private static final String[] columnNames = {"Client ID", "Name", "Contact Info", "Properties Owned"};
    private static final String[] searchArray = {"Client ID", "Name"};
    
    public Client() {
        setSize(1000, 600);
        setLayout(null);
        
        linkedlist = new LinkedList<>();
        tableModel = new DefaultTableModel(columnNames, 0);
       
        //L panels
        pnlClientForm = new JPanel();
        pnlClientForm.setLayout(null);
        pnlClientForm.setBounds(5, 60, 400, 480);
        pnlClientForm.setBackground(Color.white);
        add(pnlClientForm);
        
        //R panels
        pnlClientTable = new JPanel();
        pnlClientTable.setLayout(null);
        pnlClientTable.setBounds(420, 60, 560, 480);
        pnlClientTable.setBackground(Color.white);
        add(pnlClientTable);
        
        //components
        //L panels Labels
        lblClientTitle = new JLabel("Client Details");
        lblClientTitle.setBounds(5, 5, 150, 30);
        pnlClientForm.add(lblClientTitle);
        
        lblClientID = new JLabel("Client ID");
        lblClientID.setBounds(10, 70, 100, 30);
        pnlClientForm.add(lblClientID);
        
        lblClientName = new JLabel("Name");
        lblClientName.setBounds(10, 120, 100, 25);
        pnlClientForm.add(lblClientName);
        
        lblContactInfo = new JLabel("Contact Info");
        lblContactInfo.setBounds(10, 170, 100, 25);
        pnlClientForm.add(lblContactInfo);
        
        lblPropertiesOwned = new JLabel("Properties Owned");
        lblPropertiesOwned.setBounds(10, 220, 130, 25);
        pnlClientForm.add(lblPropertiesOwned);
                
        //L panels TextF
        txfClientID = new JTextField();
        txfClientID.setBounds(140, 70, 150, 25);
        pnlClientForm.add(txfClientID);
        
        txfClientName = new JTextField();
        txfClientName.setBounds(140, 120, 150, 25);
        pnlClientForm.add(txfClientName);
        
        txfContactInfo = new JTextField();
        txfContactInfo.setBounds(140, 170, 150, 25);
        pnlClientForm.add(txfContactInfo);
         
        txfPropertiesOwned = new JTextField();
        txfPropertiesOwned.setBounds(140, 220, 150, 25);
        pnlClientForm.add(txfPropertiesOwned);
        
        //Button
        btnAddClient = new JButton("Add Client");
        btnAddClient.setBounds(10, 280, 280, 30);
        btnAddClient.addActionListener(this);
        pnlClientForm.add(btnAddClient);
        
        btnUpdateClient = new JButton("Update Client");
        btnUpdateClient.setBounds(190, 280, 170, 30);
        btnUpdateClient.addActionListener(this);
        pnlClientForm.add(btnUpdateClient);
        
        btnDeleteClient = new JButton("Delete Client");
        btnDeleteClient.setBounds(10, 320, 350, 30);
        btnDeleteClient.addActionListener(this);
        pnlClientForm.add(btnDeleteClient);
        
        //right panel
        lblTableTitle = new JLabel("Client Records");
        lblTableTitle.setBounds(10, 10, 150, 25);
        pnlClientTable.add(lblTableTitle);
        
        btnSearchClient = new JButton("Search");
        btnSearchClient.setBounds(200, 10, 100, 25);
        btnSearchClient.addActionListener(this);
        pnlClientTable.add(btnSearchClient);
        
        //Panel Table
        tblClientRecords = new JTable(tableModel);
        tblClientRecords.setEnabled(true);
        tblClientRecords.getTableHeader().setReorderingAllowed(false);
        tblClientRecords.getTableHeader().setResizingAllowed(false);
        
        scrollpane = new JScrollPane(tblClientRecords);
        scrollpane.setBounds(10, 45,540, 420);
        pnlClientTable.add(scrollpane); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddClient) {
            String id = txfClientID.getText().trim();
            String name = txfClientName.getText().trim();
            String contact = txfContactInfo.getText().trim();
            String properties = txfPropertiesOwned.getText().trim();
            
            if (!id.isEmpty()&& !name.isEmpty() && !contact.isEmpty() && !properties.isEmpty()) {
                Object[] row = {id, name, contact, properties};
                tableModel.addRow(row);
                linkedlist.add(Arrays.toString(row));
                
                txfClientID.setText("");
                txfClientName.setText("");
                txfContactInfo.setText("");
                txfPropertiesOwned.setText("");
                
                JOptionPane.showMessageDialog(this, "Client added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Please complete alll fields", "Error", JOptionPane.ERROR_MESSAGE );      
            }
        }
        
        else if (e.getSource() == btnDeleteClient) {
            int selectedRow = tblClientRecords.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Client delete successfully1", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Please select a client from the table to delete", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
        }
        
    }
    
}
