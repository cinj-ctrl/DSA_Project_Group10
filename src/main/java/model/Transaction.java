/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GRRD
 */
public class Transaction {
    private String transactionId;
    private String propertyId;
    private String clientId;
    private String date;
    
    //CONSTRUCTOR
    public Transaction(String transactionId, String propertyId,String clientId, String date){
        
        this.transactionId = transactionId;
        this.propertyId = propertyId;
        this.clientId = clientId;
        this.date = date;
    }
    
    //GETTERS AND SETTERS
    public String getTransactionId(){return transactionId;}
    public void setTransactionId(String transactionId){this.transactionId = transactionId;}
    
    public String getPropertyId(){return propertyId;}
    public void setPropertyId(String propertyId){this.propertyId = propertyId;}
    
    public String getClientId(){return clientId;}
    public void setClientId(String clientId){this.clientId = clientId;}
    
    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}
}
