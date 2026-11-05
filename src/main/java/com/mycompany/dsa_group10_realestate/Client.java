/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_group10_realestate;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

import model.client;
import service.ClientRegistryService;

public class Client extends JPanel implements ActionListener {

    private JPanel pnlClientForm, pnlClientTable;
    private JLabel lblClientTitle, lblClientID, lblClientName;
    private JLabel lblContactInfo, lblPropertiesOwned, lblTableTitle;
    private JLabel lblSearch;

    private JTextField txfClientID, txfClientName;
    private JTextField txfContactInfo, txfPropertiesOwned;
    private JTextField txfSearchClient;

    private JButton btnAddClient, btnUpdateClient;
    private JButton btnDeleteClient, btnSearchClient; 

    private JComboBox<String> cbSearchClient;
    private JTable tblClientRecords;
    private JScrollPane scrollpane;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    private ClientRegistryService clientService;

    private static final String[] columnNames = {
        "Client ID", "Name", "Contact Info", "Properties Owned"
    };

    public Client() {

        setSize(1000, 600);
        setLayout(null);

        clientService = new ClientRegistryService();

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // LEFT PANEL
        pnlClientForm = new JPanel();
        pnlClientForm.setLayout(null);
        pnlClientForm.setBounds(5, 60, 400, 480);
        pnlClientForm.setBackground(Color.WHITE);
        add(pnlClientForm);

        // RIGHT PANEL
        pnlClientTable = new JPanel();
        pnlClientTable.setLayout(null);
        pnlClientTable.setBounds(420, 60, 560, 480);
        pnlClientTable.setBackground(Color.WHITE);
        add(pnlClientTable);

        // FORM TITLE
        lblClientTitle = new JLabel("Client Details");
        lblClientTitle.setBounds(10, 10, 150, 30);
        pnlClientForm.add(lblClientTitle);

        // CLIENT ID
        lblClientID = new JLabel("Client ID:");
        lblClientID.setBounds(20, 70, 110, 30);
        pnlClientForm.add(lblClientID);

        txfClientID = new JTextField();
        txfClientID.setBounds(140, 70, 220, 25);
        pnlClientForm.add(txfClientID);

        // NAME
        lblClientName = new JLabel("Name:");
        lblClientName.setBounds(20, 120, 110, 30);
        pnlClientForm.add(lblClientName);

        txfClientName = new JTextField();
        txfClientName.setBounds(140, 120, 220, 25);
        pnlClientForm.add(txfClientName);

        // CONTACT INFO
        lblContactInfo = new JLabel("Contact Info:");
        lblContactInfo.setBounds(20, 170, 110, 30);
        pnlClientForm.add(lblContactInfo);

        txfContactInfo = new JTextField();
        txfContactInfo.setBounds(140, 170, 220, 25);
        pnlClientForm.add(txfContactInfo);

        // PROPERTIES OWNED
        lblPropertiesOwned = new JLabel("Properties Owned:");
        lblPropertiesOwned.setBounds(20, 220, 120, 30);
        pnlClientForm.add(lblPropertiesOwned);

        txfPropertiesOwned = new JTextField();
        txfPropertiesOwned.setBounds(140, 220, 220, 25);
        pnlClientForm.add(txfPropertiesOwned);

        // BUTTONS
        btnAddClient = new JButton("Add");
        btnAddClient.setBounds(20, 290, 100, 35);
        btnAddClient.addActionListener(this);
        pnlClientForm.add(btnAddClient);

        btnUpdateClient = new JButton("Update");
        btnUpdateClient.setBounds(140, 290, 100, 35);
        btnUpdateClient.addActionListener(this);
        pnlClientForm.add(btnUpdateClient);

        btnDeleteClient = new JButton("Delete");
        btnDeleteClient.setBounds(260, 290, 100, 35);
        btnDeleteClient.addActionListener(this);
        pnlClientForm.add(btnDeleteClient);

        // RIGHT PANEL TITLE
        lblTableTitle = new JLabel("Client Records");
        lblTableTitle.setBounds(10, 5, 150, 30);
        pnlClientTable.add(lblTableTitle);

        // SEARCH
        lblSearch = new JLabel("Search By:");
        lblSearch.setBounds(10, 40, 80, 25);
        pnlClientTable.add(lblSearch);

        cbSearchClient = new JComboBox<>(
            new String[]{"Client ID", "Name"}
        );
        cbSearchClient.setBounds(85, 40, 100, 25);
        pnlClientTable.add(cbSearchClient);

        txfSearchClient = new JTextField();
        txfSearchClient.setBounds(195, 40, 210, 25);
        pnlClientTable.add(txfSearchClient);

        btnSearchClient = new JButton("Search");
        btnSearchClient.setBounds(415, 40, 100, 25);
        btnSearchClient.addActionListener(this);
        pnlClientTable.add(btnSearchClient);

        // TABLE
        tblClientRecords = new JTable(tableModel);
        tblClientRecords.getTableHeader().setReorderingAllowed(false);

        sorter = new TableRowSorter<>(tableModel);
        tblClientRecords.setRowSorter(sorter);

        scrollpane = new JScrollPane(tblClientRecords);
        scrollpane.setBounds(10, 80, 540, 380);
        pnlClientTable.add(scrollpane);

        // SELECT TABLE ROW
        tblClientRecords.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblClientRecords.getSelectedRow();

                if (selectedRow != -1) {
                    int row = tblClientRecords.convertRowIndexToModel(selectedRow);

                    txfClientID.setText(tableModel.getValueAt(row, 0).toString());
                    txfClientName.setText(tableModel.getValueAt(row, 1).toString());
                    txfContactInfo.setText(tableModel.getValueAt(row, 2).toString());
                    txfPropertiesOwned.setText(tableModel.getValueAt(row, 3).toString());

                    txfClientID.setEditable(false);
                }
            }
        });

        // LOAD DATABASE RECORDS
        loadClients();
    }

    // LOAD CLIENTS
    private void loadClients() {
        try {
            tableModel.setRowCount(0);

            for (client c : clientService.getAllClients()) {
                tableModel.addRow(new Object[]{
                    c.getClientId(),
                    c.getName(),
                    c.getContactInfo(),
                    c.getPropertiesOwned()
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, ex.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // CLEAR FIELDS
    private void clearFields() {
        txfClientID.setText("");
        txfClientName.setText("");
        txfContactInfo.setText("");
        txfPropertiesOwned.setText("");

        txfClientID.setEditable(true);
        tblClientRecords.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ADD CLIENT
        if (e.getSource() == btnAddClient) {

            String id = txfClientID.getText().trim();
            String name = txfClientName.getText().trim();
            String contact = txfContactInfo.getText().trim();
            String properties = txfPropertiesOwned.getText().trim();

            if (id.isEmpty() || name.isEmpty()
                    || contact.isEmpty() || properties.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this, "Please complete all fields."
                );
                return;
            }

            try {
                client c = new client(id, name, contact, properties);
                clientService.addClient(c);

                loadClients();
                clearFields();

                JOptionPane.showMessageDialog(
                    this, "Client added successfully!"
                );

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    this, ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // UPDATE CLIENT
        else if (e.getSource() == btnUpdateClient) {

            int selectedRow = tblClientRecords.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this, "Please select a client to update."
                );
                return;
            }

            String id = txfClientID.getText().trim();
            String name = txfClientName.getText().trim();
            String contact = txfContactInfo.getText().trim();
            String properties = txfPropertiesOwned.getText().trim();

            if (name.isEmpty() || contact.isEmpty() || properties.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Please complete all fields."
                );
                return;
            }

            try {
                client c = new client(id, name, contact, properties);
                clientService.updateClient(c);

                loadClients();
                clearFields();

                JOptionPane.showMessageDialog(
                    this, "Client updated successfully!"
                );

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    this, ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // DELETE CLIENT
        else if (e.getSource() == btnDeleteClient) {

            int selectedRow = tblClientRecords.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this, "Please select a client to delete."
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this client?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String id = txfClientID.getText().trim();

                try {
                    clientService.deleteClient(id);

                    loadClients();
                    clearFields();

                    JOptionPane.showMessageDialog(
                        this, "Client deleted successfully!"
                    );

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                        this, ex.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }

        // SEARCH CLIENT
        else if (e.getSource() == btnSearchClient) {

            String searchText = txfSearchClient.getText().trim();

            if (searchText.isEmpty()) {
                sorter.setRowFilter(null);
                return;
            }

            int column = cbSearchClient.getSelectedIndex();

            sorter.setRowFilter(
                RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(searchText), column)
            );
        }
    }
}