/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsa_group10_realestate;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ClientTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame app = new JFrame("Client Test");

            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setSize(1000, 600);

            Client client = new Client();
            app.add(client);

            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}