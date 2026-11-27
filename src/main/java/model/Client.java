/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Client {
    private String clientId;
    private String name;
    private String contactInfo;
    private double budget;
        
    //CONSTRUCTOR
    public Client(String clientId, String name, String contactInfo, double budget ){
        this.clientId = clientId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.budget = budget;  
    }
    
    //GETTERS AND SETTERS
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
}

