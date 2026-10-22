package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elizabeth mariano
 */
public class ReportControls extends JPanel implements ActionListener{
    
    private JPanel pnlControls, pnlSummary;
    private JLabel lblMetrics;
    private JComboBox<String> cbSorting;
    private JButton btnGenerateReport;
    private JTextArea txtSummary;
    private JScrollPane scrollPane;
    private LinkedList<Object> linkedlist;
    
    public ReportControls(){
        setLayout(null);
        setBackground(Color.white);
        
        linkedlist = new LinkedList<>();
        
        pnlControls = new JPanel();
        pnlControls.setLayout(null);
        pnlControls.setBounds(15, 15, 955, 90);
        pnlControls.setBackground(Color.white);
        pnlControls.setBorder(BorderFactory.createTitledBorder("Report COntrols"));
        add(pnlControls);
        
        lblMetrics = new JLabel("Metrics / Sorting:");
        lblMetrics.setBounds(20, 25, 130, 30);
        pnlControls.add(lblMetrics);
        
        String[] sortingOptions = {
            "Sort by Price (High to Low)",
            "Sort by Price (Low to High)",
            "Sort by Location (A-Z)"
        };
        
        cbSorting = new JComboBox<>(sortingOptions);
        cbSorting.setBounds(155, 27, 250, 25);
        pnlControls.add(cbSorting);
        
        btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.setBounds(420, 27, 150, 25);
        pnlControls.add(btnGenerateReport);
        
        pnlSummary = new JPanel();
        pnlSummary.setLayout(null);
        pnlSummary.setBounds(15, 115, 955, 400);
        pnlSummary.setBackground(Color.white);
        pnlSummary.setBorder(BorderFactory.createTitledBorder("Analytics Summary"));
        add(pnlSummary);
        
        txtSummary = new JTextArea();
        txtSummary.setEditable(false);
        txtSummary.setLineWrap(true);
        txtSummary.setWrapStyleWord(true);
        
        scrollPane = new JScrollPane(txtSummary);
        scrollPane.setBounds(10, 25, 930, 360);
        pnlSummary.add(scrollPane);
        
        btnGenerateReport.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnGenerateReport){
            String selectedSorting = cbSorting.getSelectedItem().toString();
            
            txtSummary.setText("");
            txtSummary.append("REAL ESTATE ANALYTICS SUMMARY\n");
            txtSummary.append("==============================\n");
            txtSummary.append("Selected Option" + selectedSorting + "\n\n");
            txtSummary.append("Report Controls are working. \n");
            txtSummary.append("Actual property data integration is still pending. \n");
        }
    }
}
