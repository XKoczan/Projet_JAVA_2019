/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillaume
 */
public class DAO_Eleve extends DAO<Eleve> {

    public DAO_Eleve() {
    super();
    }

    public void creer(Eleve obj) {
        try {
            String nom;
            String prenom;
            String type;
            int id;
            Scanner sc = new Scanner(System.in);
            System.out.println("nom");
            nom = sc.nextLine();
            System.out.println("prenom");
            prenom = sc.nextLine();
            
//            int id= obj.getId();

            Scanner sc1 = new Scanner(System.in);
            System.out.println("id");
            id = sc1.nextInt();
            
            PreparedStatement stmt = conn.prepareStatement("insert into tab_personne(personne_nom,personne_prenom,personne_type) values(?,?,'etu')");
            stmt.setObject(1, nom, Types.VARCHAR);
            stmt.setObject(2, prenom, Types.VARCHAR);
//            stmt.setObject(3, type, Types.VARCHAR);
            stmt.executeUpdate();
            
            String classe_id;
             System.out.println("Entrer l'id de la classe du nouvel eleve");
            classe_id = sc.nextLine();
            PreparedStatement stmt1 = conn.prepareStatement("insert into tab_inscription(inscription_classe_id,inscription_personne_id) values(?,?)");
            stmt1.setObject(1, classe_id, Types.INTEGER);
            stmt1.setObject(2, id, Types.INTEGER);
            stmt1.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("Probleme de creation");
        }

    }

    /*On crée un élement dans la BDD par la requete SQL insert into**/
    @Override
    public void supprimer(Eleve obj) {
        try {
            int id;
            Scanner sc= new Scanner(System.in);
            //Avec Id de la classe 
//            id = obj.getId();
            System.out.println("Saisir l'id de la suppression a faire");
            id= sc.nextInt();
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tab_personne where personne_id = ?");
            stmt.setObject(1, id, Types.INTEGER);
//            stmt.executeUpdate("DELETE FROM tab_personne where personne_id = 11");
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme de creation");
        }
    }

    /*On modifie un élement dans la BDD par la requete SQL Update set en utilisant where pour indiquer l'élément à modifier, on utilise l'id**/
    @Override
    public void modifier(Eleve obj) {
        try {
            int id;
            String nom;
            String prenom;
            String type;
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Saisir l'id de la modification a faire");
            id= sc.nextInt();
            System.out.println("modif nom");
            nom = sc1.nextLine();
            System.out.println("modif prenom");
            prenom = sc1.nextLine();
            System.out.println("modif type etu/prof");
            type = sc1.nextLine();
            
            // ID ave cla classe ELeve
//            id = obj.getId();

            PreparedStatement stmt = conn.prepareStatement("Update tab_personne set personne_nom = ? ,personne_prenom = ? ,personne_type = ? where personne_id = ? ");
            stmt.setObject(1,nom,Types.VARCHAR);
            stmt.setObject(2, prenom, Types.VARCHAR);                      
            stmt.setObject(3, type, Types.VARCHAR);
            stmt.setObject(4, id, Types.INTEGER);
            stmt.executeUpdate();
            
           
        } catch (SQLException ex) {
            System.out.println("Probleme de modification");
        }
    }

    @Override
    public void afficher(int id) {
        try {
            //Test de lecture et d'ajout
            
            //Requête SQL 
            Statement stmt = conn.createStatement();
            //Affichage des valeurs dans la table (test)
            ResultSet rs = stmt.executeQuery("select * from tab_personne where id_personne=?");
            
            while (rs.next()) {

                String a = rs.getString("Id");
                String b = rs.getString("Nom");
                String c = rs.getString("Prenom");
                int d = rs.getInt("Age");
                System.out.println("a: " + a + " b: " + b + " c: " + c + " d: " + d);

            }//fin du test

            //requete ajout dans une table
            stmt.executeUpdate("insert into tab_melltorp(Id,Nom,Prenom,Age) values(4,'Meunier','Helene',22)");
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Eleve.class.getName()).log(Level.SEVERE, null, ex);
        }

            
     
    }

}
