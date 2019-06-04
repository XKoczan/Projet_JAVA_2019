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
 
///Code qui connecte

        //On peut demander à l'utilisateur de saisir ces données là sur une fenêtre de connexion
        //https://www.experts-exchange.com/questions/27403504/JAVA-JDBC.html
        //Information pour connexion à la base de données
//        System.out.println("MySQL Connect Example.");
//        Connection conn = null;
//        String url = "jdbc:mysql://localhost/";
//        String dbName = "gestion_ecole";
//        String driver = "com.mysql.jdbc.Driver";
//        String userName = "root";
//        String password = "";
        // Fin de l'initialisation des parametres de connexion
        
        try {

            //Test avec DAO: On fait tourjours appel à la connection pour nos méthodes, elle est initialisée dans le main plus haut
            //Création d'un élève
            
            DAO_Eleve Testeleve=new DAO_Eleve();
            Eleve toto=new Eleve();
            //Ecriture dans la BDD -> voir le code dans DAO_Eleve
//            Testeleve.creer(toto);//Test creation
//            Testeleve.modifier(toto);
//            Testeleve.supprimer(toto);
            Testeleve.afficher(108);

        } catch (Exception e) {
           System.out.println("pb dans le main");
        }
    }

}
