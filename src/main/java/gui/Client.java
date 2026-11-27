/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    private JButton btnDeleteClient, btnClearForm, btnSearchClient;

    private JComboBox<String> cbSearchClient;
    private JTable tblClientRecords;
    private JScrollPane scrollpane;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    private ClientRegistryService clientService;
    private boolean isUpdatingTable = false;

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
        btnAddClient.setBounds(20, 290, 80, 35);
        btnAddClient.addActionListener(this);
        pnlClientForm.add(btnAddClient);

        btnUpdateClient = new JButton("Update");
        btnUpdateClient.setBounds(110, 290, 80, 35);
        btnUpdateClient.addActionListener(this);
        pnlClientForm.add(btnUpdateClient);

        btnDeleteClient = new JButton("Delete");
        btnDeleteClient.setBounds(200, 290, 80, 35);
        btnDeleteClient.addActionListener(this);
        pnlClientForm.add(btnDeleteClient);

        btnClearForm = new JButton("Clear");
        btnClearForm.setBounds(290, 290, 80, 35);
        btnClearForm.addActionListener(this);
        pnlClientForm.add(btnClearForm);

        // RIGHT PANEL TITLE
        lblTableTitle = new JLabel("Client Records");
        lblTableTitle.setBounds(10, 5, 150, 30);
        pnlClientTable.add(lblTableTitle);

        // SEARCH
        lblSearch = new JLabel("Search By:");
        lblSearch.setBounds(10, 40, 80, 25);
        pnlClientTable.add(lblSearch);

        cbSearchClient = new JComboBox<>(new String[]{"Client ID", "Name"});
        cbSearchClient.setBounds(85, 40, 100, 25);
        cbSearchClient.addActionListener(e -> applySearchFilter());
        pnlClientTable.add(cbSearchClient);

        txfSearchClient = new JTextField();
        txfSearchClient.setBounds(195, 40, 210, 25);
        txfSearchClient.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applySearchFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applySearchFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                applySearchFilter();
            }
        });
        pnlClientTable.add(txfSearchClient);

        btnSearchClient = new JButton("Search");
        btnSearchClient.setBounds(415, 40, 100, 25);
        btnSearchClient.addActionListener(this);
        pnlClientTable.add(btnSearchClient);

        // TABLE
        tblClientRecords = new JTable(tableModel);
        tblClientRecords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblClientRecords.getTableHeader().setReorderingAllowed(false);

        sorter = new TableRowSorter<>(tableModel);
        tblClientRecords.setRowSorter(sorter);

        scrollpane = new JScrollPane(tblClientRecords);
        scrollpane.setBounds(10, 80, 540, 380);
        pnlClientTable.add(scrollpane);

        // SELECT TABLE ROW
        tblClientRecords.getSelectionModel().addListSelectionListener(e -> {
            if (isUpdatingTable || e.getValueIsAdjusting()) {
                return;
            }

            int selectedRow = tblClientRecords.getSelectedRow();
            if (selectedRow != -1 && selectedRow < tblClientRecords.getRowCount()) {
                int modelRow = tblClientRecords.convertRowIndexToModel(selectedRow);
                if (modelRow >= 0 && modelRow < tableModel.getRowCount()) {
                    txfClientID.setText(getCellString(modelRow, 0));
                    txfClientName.setText(getCellString(modelRow, 1));
                    txfContactInfo.setText(getCellString(modelRow, 2));
                    txfPropertiesOwned.setText(getCellString(modelRow, 3));
                    txfClientID.setEditable(false);
                }
            }
        });

        loadClients();
    }

    private String getCellString(int row, int col) {
        Object value = tableModel.getValueAt(row, col);
        return value == null ? "" : value.toString();
    }

    private int getSearchColumnIndex() {
        Object selected = cbSearchClient.getSelectedItem();
        if ("Name".equals(selected)) {
            return 1;
        }
        return 0;
    }

    private void applySearchFilter() {
        String searchText = txfSearchClient.getText().trim();
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }
        int column = getSearchColumnIndex();
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(searchText), column));
    }

    // LOAD CLIENTS
    private void loadClients() {
        isUpdatingTable = true;
        try {
            tblClientRecords.clearSelection();
            tableModel.setRowCount(0);

            for (model.Client c : clientService.getAllClients()) {
                tableModel.addRow(new Object[]{
                    c.getClientId(),
                    c.getName(),
                    c.getContactInfo(),
                    c.getBudget()
                });
            }

            applySearchFilter();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Failed to load clients: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            isUpdatingTable = false;
        }
    }

    // CLEAR FIELDS
    private void clearFields() {
        isUpdatingTable = true;
        try {
            tblClientRecords.clearSelection();
        } finally {
            isUpdatingTable = false;
        }
        txfClientID.setText("");
        txfClientName.setText("");
        txfContactInfo.setText("");
        txfPropertiesOwned.setText("");
        txfClientID.setEditable(true);
        txfClientID.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ADD CLIENT
        if (source == btnAddClient) {
            String id = txfClientID.getText().trim();
            String name = txfClientName.getText().trim();
            String contact = txfContactInfo.getText().trim();
            String properties = txfPropertiesOwned.getText().trim();

            if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || properties.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete all fields.");
                return;
            }

            try {
                double budget = Double.parseDouble(properties);
                model.Client c = new model.Client(id, name, contact, budget);
                clientService.addClient(c);

                loadClients();
                clearFields();

                JOptionPane.showMessageDialog(this, "Client added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number for Properties Owned / Budget.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // UPDATE CLIENT
        else if (source == btnUpdateClient) {
            int selectedRow = tblClientRecords.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a client to update.");
                return;
            }

            String id = txfClientID.getText().trim();
            String name = txfClientName.getText().trim();
            String contact = txfContactInfo.getText().trim();
            String properties = txfPropertiesOwned.getText().trim();

            if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || properties.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please complete all fields.");
                return;
            }

            try {
                double budget = Double.parseDouble(properties);
                model.Client c = new model.Client(id, name, contact, budget);
                clientService.updateClient(c);

                loadClients();
                clearFields();

                JOptionPane.showMessageDialog(this, "Client updated successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid number for Properties Owned / Budget.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // DELETE CLIENT
        else if (source == btnDeleteClient) {
            int selectedRow = tblClientRecords.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a client to delete.");
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

                    JOptionPane.showMessageDialog(this, "Client deleted successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }

        // CLEAR FORM
        else if (source == btnClearForm) {
            clearFields();
        }

        // SEARCH CLIENT
        else if (source == btnSearchClient) {
            applySearchFilter();
        }
    }
}