/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author jobelle
 */
import model.Property;
import service.propertyServices;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author jobelle
 */
public class propertiesGUI extends JPanel implements ActionListener{
    
   
   // JTextField;
   private JButton btnAdd, btnUpdate, btnDelete, btnSearch;
   private JLabel panLabel, propertyDetail, propertyID, Location, Price, Status, PropertyReg, searchBy;
   private JPanel panelLeft, panRight;
   private JTextField txtID, txtloc, txtprice, txtSearch;
   private JComboBox<String> cbStatus, cbSearchBy;
   private JTable regTable;
   private DefaultTableModel tmodel;
   private JScrollPane scrollpane;
   private LinkedList <Object> lkdlist;
   private propertyServices propServ;
   private String[] column= {"Property ID","Location","Price","Status"};
   
       

    
    
    propertiesGUI(){
        
        setSize(1000, 600);
        setLayout(null);
        
        this.propServ = new propertyServices();
        tmodel = new DefaultTableModel(column, 0);
        lkdlist = new LinkedList();
        
        panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBounds(5, 0, 400, 600);
        panelLeft.setBackground(Color.white);
        add(panelLeft);
        
        panRight = new JPanel();
        panRight.setLayout(null);
        panRight.setBounds(420, 0, 560, 600);
        panRight.setBackground(Color.white);
        add(panRight);
        
        //Left panel
        propertyDetail = new JLabel("Property Details");
        propertyDetail.setBounds(5, 5, 100, 50);
        panelLeft.add(propertyDetail);
        
        propertyID = new JLabel("Property ID:");
        propertyID.setBounds(20, 100, 100, 50);
        panelLeft.add(propertyID);
        
        txtID = new JTextField();
        txtID.setBounds(170, 115, 200, 20);
        panelLeft.add(txtID);
        
        Location = new JLabel("Location:");
        Location.setBounds(20, 150, 100, 50);
        panelLeft.add(Location);
        
        txtloc = new JTextField();
        txtloc.setBounds(170, 165, 200, 20);
        panelLeft.add(txtloc);
        
        Price = new JLabel("Price:");
        Price.setBounds(20, 200, 100, 50);
        panelLeft.add(Price);
        
        txtprice = new JTextField();
        txtprice.setBounds(170, 215, 200, 20);
        panelLeft.add(txtprice);
        
        Status = new JLabel("Status:");
        Status.setBounds(20, 250, 100, 50);
        panelLeft.add(Status);
        String[] statusOpt = {"Available","Pending","Sold"};
        
        cbStatus = new JComboBox<>(statusOpt);
        cbStatus.setBounds(170, 265, 200, 20);
        panelLeft.add(cbStatus);
        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(50, 330, 80, 30);
        panelLeft.add(btnAdd);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(150, 330, 80, 30);
        panelLeft.add(btnUpdate);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(250, 330, 80, 30);
        panelLeft.add(btnDelete);
        
        
         //Right panel
        PropertyReg = new JLabel("Property Registry");
        PropertyReg.setBounds(5, 5, 100, 50);
        panRight.add(PropertyReg);
        
        searchBy = new JLabel("Search By:");
        searchBy.setBounds(5, 30, 100, 50);
        panRight.add(searchBy);
        
        cbSearchBy = new JComboBox<>();
        cbSearchBy.setBounds(70, 45, 100, 20);
        panRight.add(cbSearchBy);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(180, 45, 150, 20);
        panRight.add(txtSearch);
        
        btnSearch = new JButton("Seach");
        btnSearch.setBounds(330, 45, 70, 20);
        panRight.add(btnSearch);
        
        
        //Table
        regTable = new JTable (tmodel);
        scrollpane = new JScrollPane(regTable);
        regTable.getTableHeader().setReorderingAllowed(false);
        regTable.getTableHeader().setResizingAllowed(false);
        scrollpane.setBounds(10, 70, 540, 420);
        panRight.add(scrollpane);
        
        
        
        
        
        
        
        
        
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);

        
        loadDataTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()== btnAdd){
           try {
                if (txtID.getText().trim().isEmpty() || txtloc.getText().trim().isEmpty() || txtprice.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill required fields.");
                    return;
                }
                Property p = new Property(
                    txtID.getText().trim(),
                    txtloc.getText().trim(),
                    Double.parseDouble(txtprice.getText().trim()),
                    cbStatus.getSelectedItem().toString()
                );
                //propServ.addProperty(p);
                
                tmodel.addRow(new Object[]{
                    txtID.getText().trim(),
                    txtloc.getText().trim(),
                    txtprice.getText().trim(),
                    cbStatus.getSelectedItem().toString()
                });
                
                txtID.setText("");
                txtloc.setText("");
                txtprice.setText("");
                cbStatus.setSelectedIndex(0);
                
                JOptionPane.showMessageDialog(this, "Property added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
       }

       else if (e.getSource()==btnDelete){
           int selectedRow = regTable.getSelectedRow();
           if(selectedRow==-1){
               JOptionPane.showMessageDialog(this, "Please Select A Property To Delete", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }try{
              String p = tmodel.getValueAt(selectedRow, 0).toString();
              propServ.deleteProperty(p);
              tmodel.removeRow(selectedRow);
               JOptionPane.showMessageDialog(this, "Property deleted.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
           }
       }
       else if(e.getSource()==btnUpdate){
           int selectedRow = regTable.getSelectedRow();
           if(selectedRow==-1){
               JOptionPane.showMessageDialog(this, "Please Select A Property To Update", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }
           try{
           if (txtID.getText().trim().isEmpty() || txtloc.getText().trim().isEmpty() || txtprice.getText().trim().isEmpty()) {
               JOptionPane.showMessageDialog(this, "Please Fill All Property Details FIrst", "Error", JOptionPane.ERROR_MESSAGE);
       }
            Property p = new Property(
                    txtID.getText().trim(),
                    txtloc.getText().trim(),
                    Double.parseDouble(txtprice.getText().trim()),
                    cbStatus.getSelectedItem().toString()
                );
            propServ.updateProperty(p);
        tmodel.setValueAt(txtID.getText().trim(), selectedRow, 0);
        tmodel.setValueAt(txtloc.getText().trim(), selectedRow, 1);
        tmodel.setValueAt(txtprice.getText().trim(), selectedRow, 2);
        tmodel.setValueAt(cbStatus.getSelectedItem().toString(), selectedRow, 3);
        
                txtID.setText("");
                txtloc.setText("");
                txtprice.setText("");
                cbStatus.setSelectedIndex(0);
           
        JOptionPane.showMessageDialog(this, "Property updated successfully!");
  
                } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error: Price must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());

                }
       }
    }
    public void loadDataTable(){
        tmodel.setRowCount(0);
     
        try{
            List<Property> propertyList = propServ.getAllProperties();
            for(Property p : propertyList){
             tmodel.addRow(new Object[]{p.getPropertyId(), p.getLocation(), p.getPrice(), p.getStatus()});   
            }

        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error occured in processing transaction. Please try again. "+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
            
                
    
    



                
