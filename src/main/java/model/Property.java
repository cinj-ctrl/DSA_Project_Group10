/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author GRRD
 */
    
    //CONSTRUCTOR
    
        public class Property {
    private String propertyId;
    private String location;
    private double price;
    private String status;

    public Property(String propertyId, String location, double price, String status) {
        this.propertyId = propertyId;
        this.location = location;
        this.price = price;
        this.status = status;
    }

    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}


    
    
    //GETTERS AND SETTERS

