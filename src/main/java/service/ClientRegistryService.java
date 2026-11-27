/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import database.DatabaseConnectionclient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Client;

public class ClientRegistryService {

    public List<Client> getAllClients() throws SQLException {
        List<Client> list = new ArrayList<>();
        String query = "SELECT * FROM clients";
        try (Connection conn = DatabaseConnectionclient.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Client(
                    rs.getString("client_id"),
                    rs.getString("name"),
                    rs.getString("contact_info"),
                    rs.getDouble("budget")
                ));
            }
        }
        return list;
    }

    public void addClient(Client c) throws SQLException {
        String query = "INSERT INTO clients (client_id, name, contact_info, budget) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionclient.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, c.getClientId());
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getContactInfo());
            pstmt.setDouble(4, c.getBudget());
            pstmt.executeUpdate();
        }
    }

    public void updateClient(Client c) throws SQLException {
        String query = "UPDATE clients SET name = ?, contact_info = ?, budget = ? WHERE client_id = ?";
        try (Connection conn = DatabaseConnectionclient.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getContactInfo());
            pstmt.setDouble(3, c.getBudget());
            pstmt.setString(4, c.getClientId());
            pstmt.executeUpdate();
        }
    }

    public void deleteClient(String clientId) throws SQLException {
        String query = "DELETE FROM clients WHERE client_id = ?";
        try (Connection conn = DatabaseConnectionclient.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, clientId);
            pstmt.executeUpdate();
        }
    }
}