/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guifl
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ///Code vu avec le prof a voir si on peut l''utiliser, un bug lors de la lecture 
//        try {
        // TODO code application logic here

//            Class.forName("com.mysql.jdbc.Driver");
//            Connection tmp=DriverManager.getConnection("jdbc:mysql://localhost/lama","root","");
        //requête:
//            Statement stm = tmp.createStatement();
//            String query=("SELECT * FROM tab_melltorp");
//            stm.executeQuery(query);
//            stm.close();
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Pb BDD");
//        }

///Code qui connecte et qui permet de select et d'afficher
        //On peut demander à l'utilisateur de saisir ces données là sur une fenêtre de connexion
        //https://www.experts-exchange.com/questions/27403504/JAVA-JDBC.html
        System.out.println("MySQL Connect Example.");
        Connection conn = null;
        String url = "jdbc:mysql://localhost/";
        String dbName = "lama";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tab_melltorp");
            while (rs.next()) {

                String a = rs.getString("Id");
                String b = rs.getString("Nom");
                String c = rs.getString("Prenom");
                int d = rs.getInt("Age");
                System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);

            }
            stmt.executeUpdate("insert into tab_melltorp(Id,Nom,Prenom,Age) values(4,'Meunier','Helene',22)");

            System.out.println("Connected to the database");
            conn.close();
            System.out.println("Disconnected from database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
    
