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

    /*On crée dans la table personne un élève avec le type étudiant, on va par la même occasion créer son inscription 
    et l'associer à une classe. Si il n'as pas de classe pour le moment on le mets dans la classe d'id 0 qui est une classe ile d'attente.
    
    **/
    public void creer(Eleve obj) {
        try {
            String nom;
            String prenom;
            String type;
            int id;
            int classe_id;
            Scanner sc = new Scanner(System.in);
            System.out.println("nom");
            nom = sc.nextLine();
            System.out.println("prenom");
            prenom = sc.nextLine();

//            int id= obj.getId();
            Scanner sc1 = new Scanner(System.in);

            PreparedStatement stmt = conn.prepareStatement("insert into tab_personne(personne_nom,personne_prenom,personne_type) values(?,?,'etu')");
            stmt.setObject(1, nom, Types.VARCHAR);
            stmt.setObject(2, prenom, Types.VARCHAR);
            stmt.executeUpdate();

            System.out.println("Entrer l'id de la classe du nouvel eleve");
            classe_id = sc1.nextInt();
//            System.out.println("0");//Test
            PreparedStatement stmt3 = conn.prepareStatement("SELECT personne_id FROM tab_personne WHERE personne_nom = ? and personne_prenom = ?");
//           System.out.println("1");//Test
            stmt3.setString(1, nom);
            stmt3.setString(2, prenom);
            // on récupère l'id de l'élève créé pour l'associé à une classe
            ResultSet rs = stmt3.executeQuery();
            System.out.println("On recupère id de l'élève");
            rs.next();

            id = 0;//Onreset l'id
            id = rs.getInt("personne_id");
            System.out.println("ID :" + id);//Test de la récupération de 'ID
            //On effectue la création dans les tables auxiliaires ensuite après, création de l'inscription dans une classe de niveau sinon dans un classe niveau faisant office de classe de stockage des étudiants
            PreparedStatement stmt1 = conn.prepareStatement("insert into tab_inscription(inscription_classe_id,inscription_personne_id) values(?,?)");
            stmt1.setObject(1, classe_id, Types.INTEGER);//On utilise l'id de la classe
            stmt1.setObject(2, id, Types.INTEGER);//On utilise l'id de l'eleve
            stmt1.executeUpdate();//On crée dans la table inscription, l'inscription d'un eleve dans une classe

        } catch (SQLException ex) {
            System.out.println("Probleme de creation");
        }

    }

    /*On supprime l'étudiant à partir de l'id de celui-ci, on prend le soin de le supprimer de la table inscription aussi**/
    @Override
    public void supprimer(Eleve obj) {
        try {
            int id;
            Scanner sc = new Scanner(System.in);
            //Avec Id de la classe 
//            id = obj.getId();
            System.out.println("Saisir l'id de la suppression a faire");
            id = sc.nextInt();
//            //On update la classe à 0
//            PreparedStatement stmt1 = conn.prepareStatement("UPDATE tab_inscription set inscription_classe_id = 0 where personne_id = ?");
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM tab_inscription where inscription_personne_id = ?");
            stmt1.setObject(1, id, Types.INTEGER);
            stmt1.executeUpdate();
            //On delete de la table personne
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tab_personne where personne_id = ?");
            stmt.setObject(1, id, Types.INTEGER);
            stmt.executeUpdate();
            //DElete l'eleve de la classe

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
            System.out.println("Saisir l'id de l'eleve a modifier");
            id = sc.nextInt();
            System.out.println("modif nom");
            nom = sc1.nextLine();
            System.out.println("modif prenom");
            prenom = sc1.nextLine();
           

            // ID ave cla classe ELeve
//            id = obj.getId();
            PreparedStatement stmt = conn.prepareStatement("Update tab_personne set personne_nom = ? ,personne_prenom = ?  where personne_id = ? ");
            stmt.setObject(1, nom, Types.VARCHAR);
            stmt.setObject(2, prenom, Types.VARCHAR);
            stmt.setObject(3, id, Types.INTEGER);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Probleme de modification");
        }
    }

    /*On affiche un eleve en fonction de son ID **/
    @Override
    public void afficher(int id) {
        try {
            //Test de lecture et d'ajout
            
            //Requête SQL 
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tab_personne");
//            stmt.setInt(1, id);
            
            //Affichage des valeurs dans la table (test)
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String a = rs.getString("personne_id");
                String b = rs.getString("personne_nom");
                String c = rs.getString("personne_prenom");
                int d = rs.getInt("personne_type");
                System.out.println("id: " + a + " nom: " + b + " prenom: " + c + " type: " + d);

            }//fin du test

        } catch (SQLException ex) {
           System.out.println("Problème d'ID ou de chargement");
        }

    }

}
