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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GRRD
 */
public class TransactionPanel extends JPanel implements ActionListener{
    
    //GUI components
    private JPanel pnlLogTransac, pnlTransacHistory;
    private JLabel lblLogTransac, lblTransacID, lblPropertyID, lblClientID, lblDate, lblTransacHistory;
    private JTextField txfTransacID, txfPropertyID, txfClientID, txfDate;
    private JButton btnLogTransac;
    
    //All three are important for showing frontend data
    private DefaultTableModel tableModel;
    private JTable tblTransacHistory;
    private JScrollPane scrollPane;
    
    //Backend data to be used in database
    private LinkedList<Object> linkedlist;
    
    //Name for table column
    private String[] columnNames= {"Transaction ID","Property ID","Client ID","Date"};
    
    public TransactionPanel() {
        
        //Sets bounds as main frame bounds
        setBounds(0,0,1000,600);
        setLayout(null);
        
        //Table model to be displayed to user via scrollPane
        tableModel = new DefaultTableModel(columnNames,0);
        
        //Initializes linked list object
        linkedlist = new LinkedList();
        
        //PANELS
        //Left Panel
        pnlLogTransac = new JPanel();
        pnlLogTransac.setLayout(null);
        pnlLogTransac.setBounds(5, 60, 400, 480);
        pnlLogTransac.setBackground(Color.white);
        pnlLogTransac.setVisible(true);
        add(pnlLogTransac);
        
        //Right Panel
        pnlTransacHistory = new JPanel();
        pnlTransacHistory.setLayout(null);
        pnlTransacHistory.setBounds(420, 60, 560, 480);
        pnlTransacHistory.setBackground(Color.white);
        pnlTransacHistory.setVisible(true);
        add(pnlTransacHistory);
        
        //COMPONENTS
        //Left Panel Labels
        lblLogTransac = new JLabel("Log Transaction");
        lblLogTransac.setBounds(5, 5, 100, 50);
        pnlLogTransac.add(lblLogTransac);
        
        lblTransacID = new JLabel("Transaction ID:");
        lblTransacID.setBounds(20, 100, 100, 50);
        pnlLogTransac.add(lblTransacID);
        
        lblPropertyID = new JLabel("Property ID:");
        lblPropertyID.setBounds(20, 150, 100, 50);
        pnlLogTransac.add(lblPropertyID);
        
        lblClientID = new JLabel("Client ID:");
        lblClientID.setBounds(20, 200, 100, 50);
        pnlLogTransac.add(lblClientID);
        
        lblDate = new JLabel("Date (YYYY-MM-DD):");
        lblDate.setBounds(20, 250, 150, 50);
        pnlLogTransac.add(lblDate);
        
        //Left Panel Text Fields
        txfTransacID = new JTextField();
        txfTransacID.setBounds(170, 115, 200, 20);
        pnlLogTransac.add(txfTransacID);
        
        txfPropertyID = new JTextField();
        txfPropertyID.setBounds(170, 165, 200, 20);
        pnlLogTransac.add(txfPropertyID);
        
        txfClientID = new JTextField();
        txfClientID.setBounds(170, 215, 200, 20);
        pnlLogTransac.add(txfClientID);
        
        txfDate = new JTextField();
        txfDate.setBounds(170, 265, 200, 20);
        pnlLogTransac.add(txfDate);
        
        //Left Panel Button
        btnLogTransac = new JButton("Log Transaction");
        btnLogTransac.setBounds(50, 330, 280, 30);
        pnlLogTransac.add(btnLogTransac);
        
        //Right Panel Label
        lblTransacHistory = new JLabel("Transaction History");
        lblTransacHistory.setBounds(5, 5, 150, 50);
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
        scrollPane.setBounds(0, 50,560,383);
        pnlTransacHistory.add(scrollPane);
        
        //Adds action listener to button
        btnLogTransac.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogTransac){
            
            //Gets value from text fields and turn them into strings
            String transacID = txfTransacID.getText().trim();
            String propertyID = txfPropertyID.getText().trim();
            String clientID = txfClientID.getText().trim();
            String date = txfDate.getText().trim();
            
            //If all the text fields have text then do this:
            if (!transacID.isEmpty() && !propertyID.isEmpty() && !clientID.isEmpty() && !date.isEmpty()){
                
                //Gets string from text fields and adds them to row object
                Object[] row = {transacID, propertyID, clientID, date};
                
                //Adds data to DefaultTableModel to be displayed in frontend
                tableModel.addRow(row);
                
                //Adds data to LinkedList to be stores in backend
                linkedlist.add(Arrays.toString(row));
                
                //Clears textFields
                txfTransacID.setText("");
                txfPropertyID.setText("");
                txfClientID.setText("");
                txfDate.setText("");
                
                //Check linked list value FOR DEBUGGING
                    //System.out.println(linkedlist);
                
            }
            //If at least ONE text field is empty: display error message:
            else{
                JOptionPane.showMessageDialog(this, "Please complete all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
    
}
