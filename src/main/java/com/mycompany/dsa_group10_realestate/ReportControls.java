package com.mycompany.dsa_group10_realestate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;

public class ReportControls extends JFrame {

    private JComboBox<String> sortingComboBox;
    private JButton generateReportButton;
    private JTextArea reportArea;

    public ReportControls() {

        // ==========================================
        // WINDOW
        // ==========================================

        setTitle("Real Estate Management System");

        setSize(1080, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);

        // ==========================================
        // HEADER
        // ==========================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                new Color(24, 34, 45)
        );

        headerPanel.setPreferredSize(
                new Dimension(1080, 84)
        );

        // ------------------------------------------
        // TITLE
        // ------------------------------------------

        JLabel titleLabel =
                new JLabel("RealEstate Pro");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        0,
                        0
                )
        );

        headerPanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        // ------------------------------------------
        // NAVIGATION BUTTONS
        // ------------------------------------------

        JPanel navigationPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                16
                        )
                );

        navigationPanel.setOpaque(false);

        JButton propertiesButton =
                new JButton("Properties");

        JButton clientsButton =
                new JButton("Clients");

        JButton transactionsButton =
                new JButton("Transactions");

        JButton reportsButton =
                new JButton("Reports");

        // Button sizes
        propertiesButton.setPreferredSize(
                new Dimension(96, 32)
        );

        clientsButton.setPreferredSize(
                new Dimension(75, 32)
        );

        transactionsButton.setPreferredSize(
                new Dimension(110, 32)
        );

        reportsButton.setPreferredSize(
                new Dimension(78, 32)
        );

        navigationPanel.add(
                propertiesButton
        );

        navigationPanel.add(
                clientsButton
        );

        navigationPanel.add(
                transactionsButton
        );

        navigationPanel.add(
                reportsButton
        );

        headerPanel.add(
                navigationPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ==========================================
        // CONTENT AREA
        // ==========================================

        JPanel contentPanel =
                new JPanel(new BorderLayout());

        contentPanel.setBackground(Color.WHITE);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        // ==========================================
        // REPORT CONTROLS
        // ==========================================

        JPanel reportControlsPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10
                        )
                );

        reportControlsPanel.setBackground(
                Color.WHITE
        );

        reportControlsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Report Controls"
                )
        );

        // ------------------------------------------
        // METRICS / SORTING LABEL
        // ------------------------------------------

        JLabel metricsLabel =
                new JLabel("Metrics / Sorting:");

        reportControlsPanel.add(
                metricsLabel
        );

        // ------------------------------------------
        // SORTING COMBO BOX
        // ------------------------------------------

        sortingComboBox =
                new JComboBox<>();

        sortingComboBox.addItem(
                "Sort by Price (High to Low)"
        );

        sortingComboBox.addItem(
                "Sort by Price (Low to High)"
        );

        sortingComboBox.addItem(
                "Sort by Location (A-Z)"
        );

        sortingComboBox.setPreferredSize(
                new Dimension(172, 25)
        );

        reportControlsPanel.add(
                sortingComboBox
        );

        // ------------------------------------------
        // GENERATE REPORT BUTTON
        // ------------------------------------------

        generateReportButton =
                new JButton("Generate Report");

        generateReportButton.setPreferredSize(
                new Dimension(125, 25)
        );

        reportControlsPanel.add(
                generateReportButton
        );

        contentPanel.add(
                reportControlsPanel,
                BorderLayout.NORTH
        );

        // ==========================================
        // ANALYTICS SUMMARY
        // ==========================================

        JPanel analyticsPanel =
                new JPanel(new BorderLayout());

        analyticsPanel.setBackground(
                Color.WHITE
        );

        analyticsPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Analytics Summary"
                )
        );

        // ------------------------------------------
        // REPORT TEXT AREA
        // ------------------------------------------

        reportArea =
                new JTextArea();

        reportArea.setEditable(false);

        reportArea.setFont(
                new Font(
                        Font.MONOSPACED,
                        Font.PLAIN,
                        13
                )
        );

        reportArea.setBackground(
                new Color(250, 250, 252)
        );

        reportArea.setForeground(
                new Color(20, 20, 60)
        );

        reportArea.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        5,
                        5,
                        5
                )
        );

        // ------------------------------------------
        // SCROLL PANE
        // ------------------------------------------

        JScrollPane reportScrollPane =
                new JScrollPane(reportArea);

        analyticsPanel.add(
                reportScrollPane,
                BorderLayout.CENTER
        );

        contentPanel.add(
                analyticsPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // ADD CONTENT TO MAIN PANEL
        // ==========================================

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // DISPLAY EMPTY REPORT AREA
        // ==========================================

        reportArea.setText("");

        // ==========================================
        // ADD MAIN PANEL TO FRAME
        // ==========================================

        add(mainPanel);
    }

    // ==============================================
    // MAIN
    // ==============================================

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new ReportControls().setVisible(true);

        });
    }
}