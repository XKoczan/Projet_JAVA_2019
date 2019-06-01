/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

    @Override
    public void creer(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into tab_personne(personne_id,personne_nom,personne_prenom,personne_type) values(11,'Koczan','Xavie','etu')");

        } catch (SQLException ex) {
            System.out.println("Probleme de creation");
        }

    }
/*On crée un élement dans la BDD par la requete SQL insert into**/
    @Override
    public void supprimer(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM tab_personne where personne_id = 11");

        } catch (SQLException ex) {
            System.out.println("Probleme de creation");
        }
    }
/*On modifie un élement dans la BDD par la requete SQL Update set en utilisant where pour indiquer l'élément à modifier, on utilise l'id**/
    @Override
    public void modifier(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("Update tab_personne set personne_id = 11 ,personne_nom = 'Meunier',personne_prenom = 'Helene' ,personne_type = 'etu' where personne_id = 11 ");

        } catch (SQLException ex) {
            System.out.println("Probleme de modification");
        }
    }

    @Override
    public void afficher(int id) {

    }

}
