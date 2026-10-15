/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GRRD
 */
public class TransactionPanel extends JPanel implements ActionListener{

    private JPanel pnlLogTransac, pnlTransacHistory;
    private JLabel lblLogTransac, lblTransacID, lblPropertyID, lblClientID, lblDate, lblTransacHistory;
    private JTextField txfTransacID, txfPropertyID, txfClientID, txfDate;
    private JButton btnLogTransac;
    private DefaultTableModel tableModel;
    private JTable tblTransacHistory;
    private JScrollPane scrollPane;
    
    public TransactionPanel() {
        
        setBounds(0,0,1000,600);
        setLayout(null);
        
        String[] columnNames= {"Transaction ID","Property ID","Client ID","Date"};
        
        tableModel = new DefaultTableModel(columnNames,0);
        
        //PANELS
        //Left Panel
        pnlLogTransac = new JPanel();
        pnlLogTransac.setLayout(null);
        pnlLogTransac.setBounds(10,75,300,450);
        pnlLogTransac.setBackground(Color.white);
        pnlLogTransac.setVisible(true);
        add(pnlLogTransac);
        
        //Right Panel
        pnlTransacHistory = new JPanel();
        pnlTransacHistory.setLayout(null);
        pnlTransacHistory.setBounds(335,75,600,450);
        pnlTransacHistory.setBackground(Color.white);
        pnlTransacHistory.setVisible(true);
        add(pnlTransacHistory);
        
        //COMPONENTS
        //Left Panel Labels
        lblLogTransac = new JLabel("Log Transaction");
        lblLogTransac.setBounds(10,0,100,25);
        pnlLogTransac.add(lblLogTransac);
        
        lblTransacID = new JLabel("Transaction ID:");
        lblTransacID.setBounds(10,150,100,25);
        pnlLogTransac.add(lblTransacID);
        
        lblPropertyID = new JLabel("Property ID:");
        lblPropertyID.setBounds(10,200,100,25);
        pnlLogTransac.add(lblPropertyID);
        
        lblClientID = new JLabel("Client ID:");
        lblClientID.setBounds(10,250,100,25);
        pnlLogTransac.add(lblClientID);
        
        lblDate = new JLabel("Date (YYYY-MM-DD):");
        lblDate.setBounds(10,300,150,25);
        pnlLogTransac.add(lblDate);
        
        //Left Panel Text Fields
        txfTransacID = new JTextField();
        txfTransacID.setBounds(150,150,125,25);
        pnlLogTransac.add(txfTransacID);
        
        txfPropertyID = new JTextField();
        txfPropertyID.setBounds(150,200,125,25);
        pnlLogTransac.add(txfPropertyID);
        
        txfClientID = new JTextField();
        txfClientID.setBounds(150,250,125,25);
        pnlLogTransac.add(txfClientID);
        
        txfDate = new JTextField();
        txfDate.setBounds(150,300,125,25);
        pnlLogTransac.add(txfDate);
        
        //Left Panel Button
        btnLogTransac = new JButton("Log Transaction");
        btnLogTransac.setBounds(10,350,265,25);
        pnlLogTransac.add(btnLogTransac);
        
        //Right Panel Label
        lblTransacHistory = new JLabel("Transaction History");
        lblTransacHistory.setBounds(10,0,150,25);
        pnlTransacHistory.add(lblTransacHistory);
        
        //Right Panel Table
        tblTransacHistory = new JTable(tableModel);
        scrollPane = new JScrollPane(tblTransacHistory);
        
            //disables editing
        tblTransacHistory.setEnabled(false);
        
            //Disables reordering of columns (idk why enabled yan by default)
        tblTransacHistory.getTableHeader().setReorderingAllowed(false);
            //Disables resizing of columns
        tblTransacHistory.getTableHeader().setResizingAllowed(false);
        
            //Bounds and add table to panels
        scrollPane.setBounds(10,35,580,400);
        pnlTransacHistory.add(scrollPane);
        
        btnLogTransac.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogTransac){
            
            String transacID = txfTransacID.getText().trim();
            String propertyID = txfPropertyID.getText().trim();
            String clientID = txfClientID.getText().trim();
            String date = txfDate.getText().trim();
            
            if (!transacID.isEmpty() && !propertyID.isEmpty() && !clientID.isEmpty() && !date.isEmpty()){
                
                //Gets string from text fields and adds them to row object
                Object[] row = {transacID, propertyID, clientID, date};
                
                //Adds data to DefaultTableModel to be displayed in frontend
                tableModel.addRow(row);
                
                //Clears textFields
                txfTransacID.setText("");
                txfPropertyID.setText("");
                txfClientID.setText("");
                txfDate.setText("");
                
            }else{
                JOptionPane.showMessageDialog(this, "Please complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
}
