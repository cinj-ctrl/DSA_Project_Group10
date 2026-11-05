/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import database.DatabaseConnection;
import model.Property;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jobelle
 */
public class propertyServices {
    public List<Property> getAllProperties() throws SQLException {
        List<Property> list = new ArrayList<>();
        String query = "SELECT * FROM properties";
        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()){
                list.add(new Property(
                    rs.getString("propertyId"),
                    rs.getString("location"),
                    rs.getDouble("price"),
                    rs.getString("status")
                ));
            }
        }
        return list;
}
    public void addProperty(Property p) throws SQLException {
        String query = "INSERT INTO properties VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, p.getPropertyId());
            pstmt.setString(2, p.getLocation());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setString(4, p.getStatus());
            pstmt.executeUpdate();
            
        }
    }
     public void updateProperty(Property p) throws SQLException {
        String query = "UPDATE properties location=?, price=?, status=? WHERE propertyId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(2, p.getLocation());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setString(4, p.getStatus());
            pstmt.setString(1, p.getPropertyId());
            pstmt.executeUpdate();
        }
    }
     public void deleteProperty(String propertyId) throws SQLException {
        String query = "DELETE FROM properties WHERE propertyId=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, propertyId);
            pstmt.executeUpdate();
        }
    }
}