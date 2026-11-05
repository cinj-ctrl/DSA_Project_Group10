/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import database.DatabaseConnection;
import java.util.List;
import model.Transaction;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author GRRD
 */
public class transactionService {
    
    public List<Transaction> getAllTransactions() throws SQLException{
        
        List<Transaction> list = new ArrayList<>();
        String query = "SELECT * FROM transactions";
        
        try(Connection conn = DatabaseConnection.getConnection(); Statement stmnt = conn.createStatement(); ResultSet rs = stmnt.executeQuery(query)){
            while(rs.next()){
                list.add(new Transaction(rs.getString("transacId"),rs.getString("clientId"),rs.getString("propertyId"),rs.getString("date")));
            }
        }

        return list;}
        
    
    
    public void addTransaction(Transaction transac) throws SQLException{
        String connQuery = "INSERT INTO transactions (transacId, propertyId, clientId, date) VALUES (?, ?, ?, ?)";
        //String updateProp = "UPDATE properties SET status='Sold' WHERE property_id=?";
        try(Connection conn = DatabaseConnection.getConnection()){
            
            //Set auto commit to false so that manual commit can be done
            conn.setAutoCommit(false);
            
            
            try(PreparedStatement pstmnt1 = conn.prepareStatement(connQuery);
                /*PreparedStatement pstmnt2 = conn.prepareStatement(updateProp)*/){
                
                pstmnt1.setString(1, transac.getTransactionId());
                pstmnt1.setString(2, transac.getPropertyId());
                pstmnt1.setString(3, transac.getClientId());
                pstmnt1.setString(4, transac.getDate());
                
                pstmnt1.executeUpdate();
                
                //pstmnt2.setString(1, transac.getPropertyId());
                //pstmnt2.executeUpdate();
                
                //Commits changes to database
                conn.commit();
            }catch (SQLException sqlEx){
                //undos changes if SQLException occurs
                conn.rollback();
                throw sqlEx;
            }
        }
    }
}


