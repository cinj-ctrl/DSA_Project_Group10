/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

public class client {

    private String clientId;
    private String name;
    private String contactInfo;
    private String propertiesOwned;

    public client(String clientId, String name, String contactInfo, String propertiesOwned) {
        this.clientId = clientId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.propertiesOwned = propertiesOwned;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPropertiesOwned() {
        return propertiesOwned;
    }

    public void setPropertiesOwned(String propertiesOwned) {
        this.propertiesOwned = propertiesOwned;
    }
}
