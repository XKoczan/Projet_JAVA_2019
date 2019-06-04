/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public DAO_Eleve(Connection conn) {
        super(conn);

    }

    public void creer(Eleve obj) {
        try {
            String nom;
            String prenom;
            String type;
            Scanner sc = new Scanner(System.in);
            System.out.println("nom");
            nom = sc.nextLine();
            System.out.println("prenom");
            prenom = sc.nextLine();
            System.out.println("type etu/prof");
            type = sc.nextLine();
            int id= obj.getId();
            
            PreparedStatement stmt = conn.prepareStatement("insert into tab_personne(personne_nom,personne_prenom,personne_type) values(?,?,?)");
            
            stmt.setObject(1, nom, Types.VARCHAR);
            stmt.setObject(2, prenom, Types.VARCHAR);
            stmt.setObject(3, type, Types.VARCHAR);
            stmt.executeUpdate();

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
            
            System.out.println("Saisir l'id de la modification a faire");
            id= sc.nextInt();
            System.out.println("modif nom");
            nom = sc.nextLine();
            System.out.println("modif prenom");
            prenom = sc.nextLine();
            System.out.println("modif type etu/prof");
            type = sc.nextLine();
            
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

    }

}
