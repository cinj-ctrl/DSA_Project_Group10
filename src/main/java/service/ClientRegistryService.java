/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;

import database.DatabaseConnection;
import model.client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRegistryService {

    public List<client> getAllClients() throws SQLException {
        List<client> list = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new client(
                    rs.getString("clientid"),
                    rs.getString("name"),
                    rs.getString("contactinfo"),
                    rs.getString("propertiesowned")
                ));
            }
        }
        return list;
    }

    public void addClient(client c) throws SQLException {
        String query = "INSERT INTO clients (clientid, name, contactinfo, propertiesowned) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, c.getClientId());
            pstmt.setString(2, c.getName());
            pstmt.setString(3, c.getContactInfo());
            pstmt.setString(4, c.getPropertiesOwned());
            pstmt.executeUpdate();
        }
    }

    public void updateClient(client c) throws SQLException {
        String query = "UPDATE clients SET name=?, contactinfo=?, propertiesowned=? WHERE clientid=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, c.getName());
            pstmt.setString(2, c.getContactInfo());
            pstmt.setString(3, c.getPropertiesOwned());
            pstmt.setString(4, c.getClientId());
            pstmt.executeUpdate();
        }
    }

    public void deleteClient(String clientId) throws SQLException {
        String query = "DELETE FROM clients WHERE clientid=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, clientId);
            pstmt.executeUpdate();
        }
    }
}

