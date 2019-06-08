/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author xavie
 */
public class Connect {
    public static Connection conn = null;
    
    public void fenetre_conn() {
        JFrame connecte = new JFrame("Connection a l'application de gestion");
        JButton button_connect = new JButton("Connexion");
        JLabel l1 = new JLabel("Utilisateur");
        JLabel l2 = new JLabel("Mot de passe");
        JLabel l3 = new JLabel("Choix du serveur");
        JTextField t1 = new JTextField("root");
        JTextField t2 = new JTextField("");
        JTextField t3 = new JTextField("MySQL");

        connecte.setLayout(new GridLayout(8, 1));
        connecte.setSize(500, 500);
        connecte.setVisible(true);

        // On créera un ActionListener 
        button_connect.addActionListener((ActionEvent e) -> {

            // On affiche une trace
            System.out.println("On rentre dans le boutton connection");

            
            String url = "jdbc:mysql://localhost/";
        String dbName = "gestion_ecole";
        String driver = "com.mysql.jdbc.Driver";
        String userName = t1.getText();
        String password = t2.getText();

            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url + dbName, userName, password);
                System.out.println("Bien connecte");
                connecte.dispose();
                InterfaceGraphique IG = new InterfaceGraphique(conn);

            } catch (Exception ezs) {
                Error err = new Error("Compte Sql introuvable");

            }
        });

        // On ajoute le bouton
        connecte.add(l1);
        connecte.add(t1);
        connecte.add(l2);
        connecte.add(t2);
        connecte.add(l3);
        connecte.add(t3);
        connecte.add(button_connect);
      
    }
}
