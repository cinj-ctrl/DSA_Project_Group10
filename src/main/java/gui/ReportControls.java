package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Property;
import service.propertyServices;

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
    private JLabel lblMetrics, statsLabel;;
    private JComboBox<String> cbSorting;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnGenerateReport;
    private JTextArea txtSummary;
    private JScrollPane scrollPane;
    private LinkedList<Object> linkedlist;
    private propertyServices properties;
    
    
    public ReportControls(){
        setLayout(null);
        setBackground(Color.white);
        this.properties = new propertyServices();
        
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
        
        statsLabel = new JLabel("Generating valuation statistics...");
        statsLabel.setBounds(20, 360, 885, 30);
        statsLabel.setFont(statsLabel.getFont().deriveFont(14f));
        add(statsLabel);
        
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
        
//        pnlSummary = new JPanel();
//        pnlSummary.setLayout(null);
//        pnlSummary.setBounds(15, 115, 955, 400);
//        pnlSummary.setBackground(Color.white);
//        pnlSummary.setBorder(BorderFactory.createTitledBorder("Analytics Summary"));
//        add(pnlSummary);
        
//        txtSummary = new JTextArea();
//        txtSummary.setEditable(false);
//        txtSummary.setLineWrap(true);
//        txtSummary.setWrapStyleWord(true);
//        
//        scrollPane = new JScrollPane(txtSummary);
//        scrollPane.setBounds(10, 25, 930, 360);
//        pnlSummary.add(scrollPane);
        
        String[] columns = {"Property ID", "Location", "Price ($)", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 115, 955, 400);
        add(scrollPane);
        
        btnGenerateReport.addActionListener(this);
        loadReportData();
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try {
            List<Property> list = properties.getAllProperties();

//            if (e.getSource() == priceAscButton) {
//                PropertyQuickSort.sort(list, true);
//                updateTableWithList(list);
//                JOptionPane.showMessageDialog(this, "Report sorted by Price: Low to High.");
//            } else if (e.getSource() == priceDescButton) {
//                PropertyQuickSort.sort(list, false);
//                updateTableWithList(list);
//                JOptionPane.showMessageDialog(this, "Report sorted by Price: High to Low.");
//            } else if (e.getSource() == locationSortButton) {
//                list.sort(Comparator.comparing(Property::getLocation));
//                updateTableWithList(list);
//                JOptionPane.showMessageDialog(this, "Report sorted alphabetically by Location.");
//            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sorting error: " + ex.getMessage());
        }
    }

    private void updateTableWithList(List<Property> list) {
        tableModel.setRowCount(0);
        double totalValuation = 0;
        int availableCount = 0;

        for (Property p : list) {
            tableModel.addRow(new Object[]{
                p.getPropertyId(),
                p.getLocation(),
                p.getPrice(),
                p.getStatus()
            });
            totalValuation += p.getPrice();
            if ("Available".equalsIgnoreCase(p.getStatus())) {
                availableCount++;
            }
        }
        statsLabel.setText(String.format("Executive Summary — Total Properties: %d | Available: %d | Total Portfolio Value: $%,.2f",
                list.size(), availableCount, totalValuation));
    }
    
    public void loadReportData() {
        tableModel.setRowCount(0);
        try {
            List<Property> list = properties.getAllProperties();
            double totalValuation = 0;
            int availableCount = 0;

            for (Property p : list) {
                tableModel.addRow(new Object[]{
                    p.getPropertyId(),
                    p.getLocation(),
                    p.getPrice(),
                    p.getStatus()
                });
                totalValuation += p.getPrice();
                if ("Available".equalsIgnoreCase(p.getStatus())) {
                    availableCount++;
                }
            }

            statsLabel.setText(String.format("Executive Summary — Total Properties: %d | Available: %d | Total Portfolio Value: $%,.2f",
                    list.size(), availableCount, totalValuation));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error generating report data: " + ex.getMessage());
        }
    }
}